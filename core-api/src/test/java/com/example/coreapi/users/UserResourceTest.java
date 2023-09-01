package com.example.coreapi.users;

import io.helidon.microprofile.tests.junit5.HelidonTest;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@HelidonTest
public class UserResourceTest {

    @Inject
    private WebTarget webTarget;

    @Test
    public void testCreateUser() {
        JsonObject userRequest = Json.createObjectBuilder()
                .add("name", "John")
                .add("age", 30)
                .build();

        Response response = webTarget.path("/api/v1/users")
                .request()
                .post(Entity.entity(userRequest, MediaType.APPLICATION_JSON_TYPE));

        assertEquals(200, response.getStatus());

        JsonObject userResponse = response.readEntity(JsonObject.class);
        assertNotNull(userResponse);
        assertNotNull(userResponse.get("id"));
    }
}
