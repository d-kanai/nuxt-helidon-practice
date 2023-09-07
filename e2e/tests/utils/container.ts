import path from "path";
import { DockerComposeEnvironment, StartedDockerComposeEnvironment, Wait } from "testcontainers";

let environment: StartedDockerComposeEnvironment;

async function startContainers() {
  const composeFilePath = path.join(__dirname, "../../../infra/local/");
  const composeFile = "docker-compose.yml";
  environment = await new DockerComposeEnvironment(composeFilePath, composeFile)
  .withStartupTimeout(300000)
  .withWaitStrategy("ui-container", Wait.forHttp("/user", 3000))
  .withWaitStrategy("bff-container", Wait.forLogMessage("Server started on"))
  .withWaitStrategy("redis-container", Wait.forLogMessage("Ready to accept connections tcp"))
  .withWaitStrategy("redis-commander-container", Wait.forLogMessage("access with browser at"))
  .withWaitStrategy("core-api-container", Wait.forLogMessage("Server started on"))
  .withWaitStrategy("core-db-container", Wait.forLogMessage("database system is ready to accept connections"))
  .withWaitStrategy("pgadmin-container", Wait.forLogMessage("Booting worker with pid"))
  .withWaitStrategy("external-api-container", Wait.forHttp("/__admin/webapp", 3001))
  .withWaitStrategy("portainer-container", Wait.forHttp("/", 9000))
  .withBuild()
  .up();
  console.log("DockerCompose environment upped.");
}

async function stopContainers() {
  if (environment) {
    await environment.down();
    console.log("DockerCompose environment downed.");
  }
}

export { startContainers, stopContainers };
