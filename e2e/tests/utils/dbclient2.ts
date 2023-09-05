import { DataSource } from "typeorm";
import { Users } from "./tests-out/Users";

export class AppDataSource {
  private static instance: DataSource;
  private constructor() {}
  static async getInstance(): Promise<DataSource> {
    if (!AppDataSource.instance) {
      AppDataSource.instance = new DataSource({
        type: "postgres",
        host: "localhost",
        port: 5432,
        username: "postgres",
        password: "postgres",
        database: "testdb",
        entities: [Users]
      });
      await AppDataSource.instance.initialize();
      console.log("Data Source has been initialized!");
    }
    return AppDataSource.instance;
  }
}
