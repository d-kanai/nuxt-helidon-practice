package io.helidon.examples.quickstart.mp.user.infra;

import io.helidon.examples.quickstart.mp.user.domain.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    public static List<User> users = new ArrayList<>();

    public User findById(int userId) {
        return users.stream().filter(user -> user.getId() == userId).findFirst().get();
    }

}
