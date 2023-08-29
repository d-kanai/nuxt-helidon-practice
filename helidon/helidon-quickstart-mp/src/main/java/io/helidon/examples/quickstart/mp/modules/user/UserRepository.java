package io.helidon.examples.quickstart.mp.modules.user;

import io.helidon.examples.quickstart.mp.modules.user.domain.User;
import io.helidon.examples.quickstart.mp.modules.user.dto.UserAddRequest;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository {
    public void addUser(User user) {
        // データベースにUserを登録するロジック
        System.out.println("UserRepository.addUser method isn't implemented.");
    }

    public User convertRequestToUser(UserAddRequest userAddRequest) {
        User user = new User();
        user.setName(userAddRequest.getName());
        user.setAge(userAddRequest.getAge());
        return user;
    }
}
