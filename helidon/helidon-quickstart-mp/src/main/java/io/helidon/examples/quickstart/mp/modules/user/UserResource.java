package io.helidon.examples.quickstart.mp.modules.user;

import io.helidon.examples.quickstart.mp.modules.user.dto.UserAddRequest;
import io.helidon.examples.quickstart.mp.modules.user.dto.UserAddResponse;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/v1/user")
public class UserResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUser(UserAddRequest request) {
        // ユーザ登録の処理（未実装）

        UserAddResponse response = new UserAddResponse("user is created.");
        return Response.status(Response.Status.CREATED).entity(response).build();
    }
}
