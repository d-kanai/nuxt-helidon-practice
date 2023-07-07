package io.helidon.examples.quickstart.mp.user.infra;

import io.helidon.examples.quickstart.mp.user.domain.User;

import java.util.Arrays;
import java.util.List;

public class UserRepository {

    List<User> users = Arrays.asList(
            new User(1, "daiki", User.STATUS.ACTIVE),
            new User(2, "nanase", User.STATUS.DISABLE)
    );

    public User findById(int userId) {
        return users.stream().filter(user -> user.getId() == userId).findFirst().get();
    }

}
