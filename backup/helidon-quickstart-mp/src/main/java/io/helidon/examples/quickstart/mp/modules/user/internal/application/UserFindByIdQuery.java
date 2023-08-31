package io.helidon.examples.quickstart.mp.modules.user.internal.application;

import io.helidon.examples.quickstart.mp.modules.user.internal.infra.UserRepository;
import io.helidon.examples.quickstart.mp.modules.user.internal.domain.User;

public class UserFindByIdQuery {

    private UserRepository userRepository;

    public UserFindByIdQuery() {
        //TODO: DI
        userRepository = new UserRepository();
    }

    public User invoke(int userId) {
        return userRepository.findById(userId);
    }
}
