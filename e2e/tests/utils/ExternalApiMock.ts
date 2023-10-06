import { WireMockRestClient } from "wiremock-rest-client";

/**
   * How to use WireMockRestClient:
   * https://github.com/kwoding/wiremock-rest-client
   */
export class ExternalApiMock {
  private static instance: WireMockRestClient;
  private constructor() {}
  static getInstance(): WireMockRestClient {
    if (!ExternalApiMock.instance) {
      return new WireMockRestClient(process.env.EXTERNAL_MOCK_URL || "");
    }
    return ExternalApiMock.instance;
  }
}
