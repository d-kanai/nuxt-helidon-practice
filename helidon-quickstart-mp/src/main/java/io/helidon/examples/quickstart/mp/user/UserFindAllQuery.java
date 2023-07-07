package io.helidon.examples.quickstart.mp.user;

import java.util.Arrays;
import java.util.List;

public class UserFindAllQuery {
    public List<User> invoke() {
        return Arrays.asList(
                new User(1, "daiki"),
                new User(2, "nanase")
        );
    }
}
