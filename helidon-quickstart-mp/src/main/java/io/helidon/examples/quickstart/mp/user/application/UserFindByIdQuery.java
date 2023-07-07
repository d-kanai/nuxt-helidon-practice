package io.helidon.examples.quickstart.mp.user.application;

import io.helidon.examples.quickstart.mp.user.infra.UserRepository;
import io.helidon.examples.quickstart.mp.user.domain.User;

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
