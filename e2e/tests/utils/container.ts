import { GenericContainer } from "testcontainers";
import path from "path";
import { exec } from "child_process";

let postgreContainer;
let wiremockContainer;

async function startContainers() {
  // PostgreSQLのコンテナを起動する
  postgreContainer = await new GenericContainer("postgres")
    .withEnvironment({
      POSTGRES_USER: "user",
      POSTGRES_PASSWORD: "password",
      POSTGRES_DB: "test",
    })
    .withExposedPorts({
      container: 5432,
      host: 5432,
    })
    .start();
  const postgreMappedPort = postgreContainer.getMappedPort(5432);
  console.log(`Started postgres container at port ${postgreMappedPort}`);

  // Wiremockのコンテナを起動する
  const dockerfilePath = path.join(__dirname, "../../../external-api/");
  wiremockContainer = await (
    await GenericContainer.fromDockerfile(dockerfilePath).build(
      "wiremock-container"
    )
  )
    .withExposedPorts({
      container: 8080,
      host: 3001,
    })
    .start();
  const wiremockMappedPort = wiremockContainer.getMappedPort(8080);
  console.log(`Started wiremock container at port ${wiremockMappedPort}`);
}

async function stopContainers() {
  // コンテナの停止
  if (postgreContainer) {
    await postgreContainer.stop();
    console.log("Stopped postgres container");
  }

  if (wiremockContainer) {
    await wiremockContainer.stop();
    console.log("Stopped wiremock container");
  }
}

/**
 * imageのゴミを削除
 */
function cleanUnusedImages() {
  exec("podman image prune -f", (error, stdout, stderr) => {
    if (error) {
      console.error(`exec error: ${error}`);
      return;
    }
    console.log(`stdout: ${stdout}`);
    console.error(`stderr: ${stderr}`);
  });
}

export { startContainers, stopContainers, cleanUnusedImages };
