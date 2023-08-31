package io.helidon.examples.quickstart.mp.modules.user;

import io.helidon.examples.quickstart.mp.modules.user.domain.User;
import io.helidon.examples.quickstart.mp.modules.user.dto.UserAddRequest;
import io.helidon.examples.quickstart.mp.modules.user.dto.UserAddResponse;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/v1/user")
@RequestScoped
public class UserResource {

    private final UserRepository userRepository;

    @Inject
    public UserResource(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUser(UserAddRequest request) {
        // UserRepositoryを使用してユーザ登録の処理を行う
        User user = new User();
        user.setName(request.getName());
        user.setAge(request.getAge());
        userRepository.addUser(user);

        UserAddResponse response = new UserAddResponse("user is created.");
        return Response.status(Response.Status.CREATED).entity(response).build();
    }
}
