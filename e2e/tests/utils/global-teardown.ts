import { type FullConfig } from "@playwright/test";
import { stopContainers } from "./container";

async function globalTeardown(config: FullConfig) {
  if (process.env.DOCKER_COMPOSE == "on") {
    await stopContainers();
  }
}

export default globalTeardown;
