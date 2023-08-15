import { Client } from "pg";

interface Customer {
  id: number;
  name: string;
}

async function createCustomerTable(client: Client): Promise<void> {
  const sql =
    "CREATE TABLE IF NOT EXISTS customers (id INT NOT NULL, name VARCHAR NOT NULL, PRIMARY KEY (id))";
  await client.query(sql);
}

async function createCustomer(
  client: Client,
  customer: Customer
): Promise<void> {
  const sql = "INSERT INTO customers (id, name) VALUES($1, $2)";
  await client.query(sql, [customer.id, customer.name]);
}

async function getCustomers(client: Client): Promise<Customer[]> {
  const sql = "SELECT * FROM customers";
  const result = await client.query(sql);
  return result.rows;
}

export { createCustomerTable, createCustomer, getCustomers, Customer };
