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
        type: process.env.DB_TYPE as any,
        connectString: process.env.DB_CONNECT_STRING, // connect by thin
        port: Number(process.env.DB_PORT),
        username: process.env.DB_USER,
        password: process.env.DB_PASS,
        entities: [Users],
      });
    }
    return DbDataSource.instance;
  }
}
