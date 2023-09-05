import { type FullConfig } from '@playwright/test';
import { startContainers } from './container';

async function globalSetup(config: FullConfig) {
  await startContainers();
}

export default globalSetup;