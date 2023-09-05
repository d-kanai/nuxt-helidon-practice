import { DataSource } from "typeorm";
import { Users } from "./db/entities-js/Users";

export class DbDataSource {
  private static instance: DataSource;
  private constructor() {}
  static async getInstance(): Promise<DataSource> {
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
      await DbDataSource.instance.initialize();
      console.log("Data Source has been initialized!");
    }
    return DbDataSource.instance;
  }
  static async distory() {
    DbDataSource.instance.destroy();
    console.log("Data Source has been destroied!");
  }
}
