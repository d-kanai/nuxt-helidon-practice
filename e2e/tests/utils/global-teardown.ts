import { type FullConfig } from '@playwright/test';
import { stopContainers } from './container';

async function globalTeardown(config: FullConfig) {
  await stopContainers();
}

export default globalTeardown;