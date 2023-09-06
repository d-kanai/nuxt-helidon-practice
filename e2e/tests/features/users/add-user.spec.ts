import { expect, test } from "@playwright/test";
import { RedisClientType } from "@redis/client";
import { DataSource, ObjectLiteral, Repository } from "typeorm";
import { WireMockRestClient } from "wiremock-rest-client";
import { Users } from "../../utils/db/entities-js/Users";
import { DbDataSource } from "../../utils/DbDataSource";
import { ExternalApiMock } from "../../utils/ExternalApiMock";
import { RedisClient } from "../../utils/RedisClient";

let dataSource: DataSource;
let userRepository: Repository<ObjectLiteral>;
let wireMockRestClient: WireMockRestClient;
let redisClient: RedisClientType<any>;

test.beforeAll(async () => {
  dataSource = await DbDataSource.getInstance();
  await dataSource.initialize();
  userRepository = dataSource.getRepository(Users);
  wireMockRestClient = ExternalApiMock.getInstance();
  redisClient = RedisClient.getInstance();
  await redisClient.connect();
});

test.beforeEach(async () => {
  await userRepository.clear();
  await wireMockRestClient.mappings.resetAllMappings();
  await redisClient.flushDb();
});

test.afterAll(async () => {
  await dataSource.destroy();
  await redisClient.disconnect();
});

test("ユーザ登録できること", async ({ page }) => {
  // Arrange
  const expectedUsers = [
    {
      name: "james.jones",
      age: 23,
    },
    {
      name: "jiadong.chen",
      age: 39,
    },
  ];

  const expectedSessionItemSize = 1;

  await wireMockRestClient.mappings.createMappingsFromDir(
    "./tests/features/users/external-api-mock"
  );
  const user = new Users();
  user.name = "james.jones";
  user.age = 23;
  await userRepository.save(user);

  // Act
  // Pageが全部ロードされるまで待つ
  await page.waitForLoadState("networkidle");
  await page.goto("http://localhost:3000/user");
  await page.getByLabel("Name").fill("jiadong.chen");
  await page.getByLabel("Age").fill("39");
  await page.getByRole("button", { name: "Submit" }).click();
  // /api/v1/users API からレスポンスが帰ってきてかつ status が200になるまで待つ
  await Promise.all([
    page.waitForResponse(
      (response) =>
        response.url().includes("/api/v1/users") && response.status() === 200,
      { timeout: 10000 }
    ),
  ]);

  const actualUsers = await userRepository.find();
  console.log(JSON.stringify(actualUsers));
  const actualUsersWithoutId = actualUsers.map(
    ({ id, ...otherProps }) => otherProps
  ); // id を除外

  const actualSessionKeys = await redisClient.keys("*");
  const actualSessionItemSize = actualSessionKeys.length;
  console.log(`redis value is ${JSON.stringify(actualSessionKeys)}`);

  // Assert
  expect(actualUsersWithoutId).toEqual(expectedUsers);
  expect(actualSessionItemSize).toBe(expectedSessionItemSize);
});
