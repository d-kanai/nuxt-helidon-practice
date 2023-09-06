import { WireMockRestClient } from "wiremock-rest-client";

export class ExternalApiMock {
  private static instance: WireMockRestClient;
  private constructor() {}
  static getInstance(): WireMockRestClient {
    if (!ExternalApiMock.instance) {
      return new WireMockRestClient("http://localhost:3001");
    }
    return ExternalApiMock.instance;
  }
}
