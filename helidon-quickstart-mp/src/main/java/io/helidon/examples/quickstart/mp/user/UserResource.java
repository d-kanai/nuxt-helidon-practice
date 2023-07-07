
package io.helidon.examples.quickstart.mp.user;

import jakarta.enterprise.context.RequestScoped;
import jakarta.json.Json;
import jakarta.json.JsonBuilderFactory;
import jakarta.json.JsonObject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.Collections;
import java.util.List;

@Path("/user")
@RequestScoped
public class UserResource {

    private static final JsonBuilderFactory JSON = Json.createBuilderFactory(Collections.emptyMap());


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject findAll() {
        List<User> users = new UserFindAllQuery().invoke();
        return JSON.createObjectBuilder()
                .add("id", users.get(0).getId())
                .add("name", users.get(0).getName())
                .build();
    }

}
