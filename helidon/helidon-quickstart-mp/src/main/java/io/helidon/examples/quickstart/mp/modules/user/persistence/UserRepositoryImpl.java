package io.helidon.examples.quickstart.mp.modules.user.persistence;


import io.helidon.examples.quickstart.mp.modules.user.domain.User;
import io.helidon.examples.quickstart.mp.modules.user.externalapi.dto.UserResponse;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
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
    private final RedisClient redisClient;
    private final StatefulRedisConnection<String, String> connection;
    private final RedisCommands<String, String> syncCommands;

    private final String coreApiHost;
    private final String coreApiPort;

    @Inject
    public UserRepositoryImpl(
            @ConfigProperty(name = "redis.host") String redisHost,
            @ConfigProperty(name = "redis.port") int redisPort,
            @ConfigProperty(name = "redis.password") String redisPassword,
            @ConfigProperty(name = "core-api.host") String coreApiHost,
            @ConfigProperty(name = "core-api.port") String coreApiPort
    ) {
        this.coreApiHost = coreApiHost;
        this.coreApiPort = coreApiPort;
        // Redisの接続情報を設定
        RedisURI redisUri = RedisURI.builder()
                .withHost(redisHost)
                .withPort(redisPort)        // ポート番号
                .withPassword(redisPassword) // パスワード
                .build();

        this.redisClient = RedisClient.create(redisUri);
        this.connection = redisClient.connect();
        this.syncCommands = connection.sync();
    }

    public void addUser(User user) {
        Long id = this.postUserToExternalAPI(user);
        String text = "User(id=%d) is posted to External api.";
        System.out.println(String.format(text, id));
        String key = "user:" + id;
        syncCommands.hset(key, "id", String.valueOf(id));
        syncCommands.hset(key, "name", user.getName());
        syncCommands.hset(key, "age", String.valueOf(user.getAge()));
        System.out.println("User added to Redis.");
    }

    public Long postUserToExternalAPI(User user) {
        String path = this.coreApiHost + ":" + this.coreApiPort + "/api/v1/users";
        System.out.println("Api Path: " + path);
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(path);
        Response response = webTarget
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
