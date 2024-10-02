package kz.kalabay.locationsystem.connection;
import javax.annotation.PostConstruct;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.data.redis.connection.RedisConnectionFactory;

@Component
@RequiredArgsConstructor
public class RedisTestConnection {
    private final RedisConnectionFactory redisConnectionFactory;

    @PostConstruct
    public void checkConnection() {
        try {
            String pingResponse = redisConnectionFactory.getConnection().ping();
            System.out.println("Connected to Redis: " + pingResponse);
        } catch (Exception e) {
            System.err.println("Failed to connect to Redis: " + e.getMessage());
        }
    }
}
