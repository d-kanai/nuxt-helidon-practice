package com.example.coreapi.users;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class ExternalApiClient {
    private final WebTarget webTarget;

    @Inject
    public ExternalApiClient(
            @ConfigProperty(name = "external-api.host") String coreApiHost,
            @ConfigProperty(name = "external-api.port") String coreApiPort
    ) {
        String baseUrl = coreApiHost + ":" + coreApiPort;
        System.out.println("Base Url Path: " + baseUrl);
        Client client = ClientBuilder.newClient();
        this.webTarget = client.target(baseUrl);
    }

    public WebTarget getWebTarget() {
        return webTarget;
    }
}
