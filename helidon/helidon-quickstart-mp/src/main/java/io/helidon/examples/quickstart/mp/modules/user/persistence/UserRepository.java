package io.helidon.examples.quickstart.mp.modules.user.persistence;


import io.helidon.examples.quickstart.mp.modules.user.domain.User;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository {
    private final RedisClient redisClient;
    private final StatefulRedisConnection<String, String> connection;
    private final RedisCommands<String, String> syncCommands;

    public UserRepository() {
        // Redisの接続情報を設定
        RedisURI redisUri = RedisURI.builder()
//                .withHost("localhost") // Redisコンテナのホスト名
                .withHost("redis-container")
                .withPort(6379)        // ポート番号
                .withPassword("testpassword") // パスワード
                .build();

        this.redisClient = RedisClient.create(redisUri);
        this.connection = redisClient.connect();
        this.syncCommands = connection.sync();
    }

    public void addUser(User user) {
        String key = "user:" + user.getName();
        syncCommands.hset(key, "name", user.getName());
        syncCommands.hset(key, "age", String.valueOf(user.getAge()));
        System.out.println("User added to Redis.");
    }
}
