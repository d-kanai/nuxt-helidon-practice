package io.helidon.examples.quickstart.mp.modules.user.internal.infra;

import io.helidon.examples.quickstart.mp.modules.user.internal.domain.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    public static List<User> users = new ArrayList<>();

    public User findById(int userId) {
        return users.stream().filter(user -> user.getId() == userId).findFirst().get();
    }

}
