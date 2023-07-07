package io.helidon.examples.quickstart.mp.user.infra;

import io.helidon.examples.quickstart.mp.user.domain.User;
import io.helidon.examples.quickstart.mp.user.expose.UserStatus;

import java.util.Arrays;
import java.util.List;

public class UserRepository {

    public static List<User> users = Arrays.asList(
            new User(1, "daiki", UserStatus.ACTIVE),
            new User(2, "nanase", UserStatus.DISABLE)
    );

    public User findById(int userId) {
        return users.stream().filter(user -> user.getId() == userId).findFirst().get();
    }

}
