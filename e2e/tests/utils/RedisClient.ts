import { createClient, RedisClientType } from "redis";

export class RedisClient {
  private static instance: RedisClientType;
  private constructor() {}
  static getInstance(): RedisClientType {
    if (!RedisClient.instance) {
      RedisClient.instance = createClient({
        url: "redis://:testpassword@localhost:6379",
      });
      RedisClient.instance.on("error", (err) =>
        console.log("Redis Client Error", err)
      );
    }
    return RedisClient.instance;
  }
}
