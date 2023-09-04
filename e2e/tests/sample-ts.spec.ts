import { test, expect } from "@playwright/test";
import { Client } from "pg";
import {
  startContainers,
  stopContainers,
} from "./utils/container";
import { createCustomer, createCustomerTable, Customer, getCustomers } from "./utils/dbclient";

const postgresClient: Client = new Client({
  host: "localhost",
  port: 5432,
  user: "postgres",
  password: "postgres",
  database: "testdb",
});

test.beforeAll(async () => {
  await startContainers();
  await postgresClient.connect();
  await createCustomerTable(postgresClient);
});

test.afterAll(async () => {
  await postgresClient.end();
  await stopContainers();
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

test("should create and return multiple customers", async () => {
  // Arrange
  const customer1: Customer = { id: 1, name: "John Doe" };
  const customer2: Customer = { id: 2, name: "Jane Doe" };

  // Act
  await createCustomer(postgresClient, customer1);
  await createCustomer(postgresClient, customer2);
  const customers: Customer[] = await getCustomers(postgresClient);
  console.log(JSON.stringify(customers));

  // Assert
  expect(customers).toEqual([customer1, customer2]);
});