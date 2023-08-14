import { test, expect } from "@playwright/test";
import { startContainers, stopContainers, cleanUnusedImages } from "./utils/container";

test.beforeAll(async () => {
  await startContainers();
});

test.afterAll(async () => {
  await stopContainers();
  cleanUnusedImages();
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
