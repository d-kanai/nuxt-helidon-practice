import { GenericContainer } from "testcontainers";
import path from "path";
import { exec } from "child_process";
import { test, expect } from "@playwright/test";

let postgreContainer;
let wiremockContainer;

test.beforeAll(async () => {
  // PostgreSQLのコンテナを起動する
  postgreContainer = await new GenericContainer("postgres")
    .withEnvironment({
      POSTGRES_USER: "user",
      POSTGRES_PASSWORD: "password",
      POSTGRES_DB: "test",
    })
    .withExposedPorts({
      container: 5432,
      host: 5432,
    })
    .start();
  const postgreMappedPort = postgreContainer.getMappedPort(5432);
  console.log(`Started postgres container at port ${postgreMappedPort}`);

  // Wiremockのコンテナを起動する
  const dockerfilePath = path.join(__dirname, "../../external-api/");
  wiremockContainer = await (
    await GenericContainer.fromDockerfile(dockerfilePath).build(
      "wiremock-container"
    )
  )
    .withExposedPorts({
      container: 8080,
      host: 3001,
    })
    .start();
  const wiremockMappedPort = wiremockContainer.getMappedPort(8080);
  console.log(`Started wiremock container at port ${wiremockMappedPort}`);

  // 必要に応じて、DB接続と初期化処理を行う
});

test.afterAll(async () => {
  // コンテナの停止
  if (postgreContainer) {
    await postgreContainer.stop();
    console.log("Stopped postgres container");
  }

  if (wiremockContainer) {
    await wiremockContainer.stop();
    console.log("Stopped wiremock container");
  }

  // imageのゴミを削除
  exec("podman image prune -f", (error, stdout, stderr) => {
    if (error) {
      console.error(`exec error: ${error}`);
      return;
    }
    console.log(`stdout: ${stdout}`);
    console.error(`stderr: ${stderr}`);
  });
});

test("ページが表示されてSample Data Listのテキストが表示されている", async ({
  page,
}) => {
  // Arrabge
  const expected = "Sample Data List";

  // Act
  await page.goto("http://localhost:3000/sample-ts");
  // caption tag の elementを取得
  const locator = page.locator("#__nuxt > div > table > caption");

  // Assert
  await expect(locator).toHaveText(expected);
});

test("api呼び出しで取得した情報がページに表示されている", async ({ page }) => {
  // Arrange

  // Act
  await page.goto("http://localhost:3000/sample-ts");
  const userIdLocator = page.getByText("User ID");
  await userIdLocator.waitFor({ state: "visible", timeout: 5000 });
  const usernameLocator = page.getByText("Username");
  await usernameLocator.waitFor({ state: "visible", timeout: 5000 });

  // user idがテーブルに表示されているか確認する
  const userIdExists = await userIdLocator.isVisible();
  // usernameがテーブルに表示されているか確認する
  const usernameExists = await usernameLocator.isVisible();

  // Assert
  expect(userIdExists).toBe(true);
  expect(usernameExists).toBe(true);
});
