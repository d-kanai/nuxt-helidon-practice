
package io.helidon.examples.quickstart.mp.user.presentation;

import io.helidon.examples.quickstart.mp.user.application.UserFindByIdQuery;
import io.helidon.examples.quickstart.mp.user.domain.User;
import io.helidon.examples.quickstart.mp.user.dto.UserFindByIdResponse;
import jakarta.enterprise.context.RequestScoped;
import jakarta.json.Json;
import jakarta.json.JsonBuilderFactory;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.Collections;

@Path("/user")
@RequestScoped
public class UserResource {

    private static final JsonBuilderFactory JSON = Json.createBuilderFactory(Collections.emptyMap());
    private UserFindByIdQuery userFindByIdQuery;

    public UserResource() {
        //TODO: DI
        userFindByIdQuery = new UserFindByIdQuery();
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public UserFindByIdResponse findById(@PathParam("id") int userId) {
        User user = userFindByIdQuery.invoke(userId);
        //TODO: mapping いい感じにする
        return new UserFindByIdResponse(user.getId(), user.getName(), user.getStatus());
    }

}

