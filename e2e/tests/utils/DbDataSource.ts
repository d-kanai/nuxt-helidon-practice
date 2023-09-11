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
        type: "oracle",
        connectString: "localhost:1521/ORCLPDB1", // connect by thin
        port: 1521,
        username: "TEST_USER",
        password: "testpassword",
        entities: [Users],
      });
    }
    return DbDataSource.instance;
  }
}
