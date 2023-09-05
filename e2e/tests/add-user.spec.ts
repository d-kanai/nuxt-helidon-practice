import { test, expect } from "@playwright/test";
import { AppDataSource } from "./utils/dbclient2"
// import { Users } from "./utils/db/entities/Users";
import { Users } from "./utils/tests-out/Users";
import { startContainers, stopContainers } from "./utils/container";

test.describe("add user", () => {
  
})

// test.beforeAll(async () => {
//   await startContainers();
// });

// test.afterAll(async () => {
//   await stopContainers();
// });

test("ユーザ登録できること", async ({ page }) => {
  // Arrange

  // Act
  await page.waitForLoadState("networkidle");
  await page.goto("http://localhost:3000/user");
  await page.getByLabel("Name").fill("jiadong.chen");
  await page.getByLabel("Age").fill("39");
  await page.getByRole("button", { name: "Submit" }).click();
  await page.waitForTimeout(200);

  const dataSource = await AppDataSource.getInstance();
  const userRepository = dataSource.getRepository(Users);
  const users = await userRepository.find();

  console.log(JSON.stringify(users));
});
