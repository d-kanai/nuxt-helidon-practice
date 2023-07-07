package io.helidon.examples.quickstart.mp.user;

import java.util.Arrays;
import java.util.List;

public class UserRepository {

    List<User> users = Arrays.asList(
            new User(1, "daiki"),
            new User(2, "nanase")
    );

    public User findById(int userId) {
        return users.stream().filter(user -> user.getId() == userId).findFirst().get();
    }

}
