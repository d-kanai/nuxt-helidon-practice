import { DataSource } from "typeorm";
import { Users } from "./db/entities-js/Users";

/**
 * How to use TypeORM:
 * https://typeorm.io/
 */
export class DbDataSource {
  private static instance: DataSource;
  private constructor() {}
  static getInstance(): DataSource {
    if (!DbDataSource.instance) {
      DbDataSource.instance = new DataSource({
        type: "postgres",
        host: "localhost",
        port: 5432,
        username: "postgres",
        password: "postgres",
        database: "testdb",
        entities: [Users],
      });
    }
    return DbDataSource.instance;
  }
}
