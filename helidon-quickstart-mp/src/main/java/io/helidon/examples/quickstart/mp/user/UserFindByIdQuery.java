package io.helidon.examples.quickstart.mp.user;

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
