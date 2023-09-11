package com.example.coreapi.users;

import io.helidon.microprofile.tests.junit5.AddBean;
import io.helidon.microprofile.tests.junit5.AddConfig;
import io.helidon.microprofile.tests.junit5.HelidonTest;
import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.metamodel.Metamodel;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@HelidonTest
@AddConfig(key = "javax.sql.DataSource.ds3.dataSource.url", value = "jdbc:oracle:thin:@//localhost:1521/ORCLPDB1")
@AddConfig(key = "external-api.host", value = "http://localhost")
@AddConfig(key = "external-api.port", value = "3001")
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
