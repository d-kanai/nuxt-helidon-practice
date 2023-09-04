package io.helidon.examples.quickstart.mp.modules.user;

import io.helidon.examples.quickstart.mp.modules.user.domain.User;
import io.helidon.examples.quickstart.mp.modules.user.dto.UserAddRequest;
import io.helidon.examples.quickstart.mp.modules.user.dto.UserAddResponse;
import io.helidon.examples.quickstart.mp.modules.user.persistence.UserRepository;
import io.helidon.examples.quickstart.mp.utils.DataTimeUtils;
import io.helidon.microprofile.cors.CrossOrigin;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/v1/users")
@RequestScoped
public class UserResource {

    private final UserRepository userRepository;
    private final DataTimeUtils dataTimeUtils;

    @Inject
    public UserResource(
            UserRepository userRepository,
            DataTimeUtils dataTimeUtils
    ) {
        this.userRepository = userRepository;
        this.dataTimeUtils = dataTimeUtils;
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
        String dataTime = this.dataTimeUtils.getDateTime();
        UserAddResponse response = new UserAddResponse(dataTime + ": user is created.");
        return Response.status(Response.Status.CREATED).entity(response).build();
    }

    @OPTIONS
    @CrossOrigin(
            value = {"*"},
            allowMethods = {"POST"}
    )
    public Response options() {
        return Response.ok().build();
    }
}
