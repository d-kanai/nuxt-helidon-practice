import { test, expect } from "@playwright/test";
import { DbDataSource } from "../../utils/DbDataSource";
import { Users } from "../../utils/db/entities-js/Users";
import { DataSource, ObjectLiteral, Repository } from "typeorm";
import { WireMockRestClient } from "wiremock-rest-client";

let dataSource: DataSource;
let userRepository: Repository<ObjectLiteral>;

test.beforeAll(async () => {
  dataSource = await DbDataSource.getInstance();
  userRepository = dataSource.getRepository(Users);
});

test.beforeEach(() => {
  userRepository.clear();
});

test.afterAll(async () => {
  await DbDataSource.distory();
});

test("ユーザ登録できること", async ({ page }) => {
  // Arrange
  const expectedUsers = [
    {
      name: "jiadong.chen",
      age: 39,
    },
  ];

  const wireMockRestClient = new WireMockRestClient("http://localhost:3001");
  await wireMockRestClient.mappings.resetAllMappings();
  const stubMapping = {
    request: {
      method: "POST",
      urlPath: "/api/v1/stat",
    },
    response: {
      status: 200,
      headers: {
        "Content-Type": "application/json",
      },
      body: '{ "message": "stat info is received." }',
    },
  };
  const response = await wireMockRestClient.mappings.createMapping(stubMapping);

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
        response.url().includes("/api/v1/users") && response.status() === 200
    ),
  ]);

  const actualUsers = await userRepository.find();
  console.log(JSON.stringify(actualUsers));
  const actualUsersWithoutId = actualUsers.map(
    ({ id, ...otherProps }) => otherProps
  ); // id を除外

  // Assert
  expect(actualUsersWithoutId).toEqual(expectedUsers);
});
