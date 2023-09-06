package io.helidon.examples.quickstart.mp.modules.user.infra;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class BffRedisClient {
    private final RedisClient redisClient;
    @Inject
    public BffRedisClient(
            @ConfigProperty(name = "redis.host") String redisHost,
            @ConfigProperty(name = "redis.port") int redisPort,
            @ConfigProperty(name = "redis.password") String redisPassword
    ) {
        // Redisの接続情報を設定
        RedisURI redisUri = RedisURI.builder()
                .withHost(redisHost)
                .withPort(redisPort)        // ポート番号
                .withPassword(redisPassword) // パスワード
                .build();

        this.redisClient = RedisClient.create(redisUri);
    }

    public RedisClient getRedisClient() {
        return this.redisClient;
    }
}
