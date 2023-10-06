import { type FullConfig } from "@playwright/test";
import { startContainers } from "./container";

async function globalSetup(config: FullConfig) {
  if (process.env.DOCKER_COMPOSE == "on") {
    await startContainers();
  }
}

export default globalSetup;
