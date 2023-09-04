package io.helidon.examples.quickstart.mp.modules.user.persistence;


import io.helidon.examples.quickstart.mp.modules.user.domain.User;
import io.helidon.examples.quickstart.mp.modules.user.externalapi.dto.UserResponse;
import io.helidon.examples.quickstart.mp.modules.user.infra.BffRedisClient;
import io.helidon.examples.quickstart.mp.modules.user.infra.ExternalApiClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class UserRepositoryImpl implements UserRepository{
    private final BffRedisClient bffRedisClient;
    private final ExternalApiClient externalApiClient;

    @Inject
    public UserRepositoryImpl(
            BffRedisClient bffRedisClient,
            ExternalApiClient externalApiClient
    ) {
        this.bffRedisClient = bffRedisClient;
        this.externalApiClient = externalApiClient;
    }

    public void addUser(User user) {
        Long id = this.postUserToExternalAPI(user);
        String text = "User(id=%d) is posted to External api.";
        System.out.println(String.format(text, id));
        String key = "user:" + id;
        StatefulRedisConnection<String, String> connection = this.bffRedisClient.getRedisClient().connect();
        RedisCommands<String, String> syncCommands = connection.sync();
        syncCommands.hset(key, "id", String.valueOf(id));
        syncCommands.hset(key, "name", user.getName());
        syncCommands.hset(key, "age", String.valueOf(user.getAge()));
        System.out.println("User added to Redis.");
    }

    public Long postUserToExternalAPI(User user) {
        String apiPath = "/api/v1/users";
        System.out.println("Api Path: " + apiPath);
        WebTarget webTarget = this.externalApiClient.getWebTarget();
        Response response = webTarget
                .path(apiPath)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(user, MediaType.APPLICATION_JSON));

        Long id = null;

        if (response.getStatus() == 201) {
            System.out.println("Successfully posted user to external API.");
            UserResponse userResponse = response.readEntity(UserResponse.class);
            id = userResponse.getId();
        } else {
            System.out.println("Failed to post user to external API. HTTP status: " + response.getStatus());
        }
        response.close();
        return id;
    }
}
