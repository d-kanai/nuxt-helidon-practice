package io.helidon.examples.quickstart.mp.modules.user.persistence;

import io.helidon.examples.quickstart.mp.modules.user.domain.User;

public interface UserRepository {
    public void addUser(User user);
}
