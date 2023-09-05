import { test, expect } from "@playwright/test";
import { startContainers, stopContainers } from "./utils/container";

test.beforeAll(async () => {
  await startContainers();
});

test.afterAll(async () => {
  await stopContainers();
});

test("ユーザ登録できること", async ({ page }) => {
  // Arrange

  // Act
  await page.waitForLoadState('networkidle');
  await page.goto("http://localhost:3000/user");
  await page.getByLabel("Name").fill("jiadong.chenb");
  await page.getByLabel("Age").fill("39");
  await page.getByRole("button", { name: "Submit" }).click();
  await page.waitForTimeout(200);
});
