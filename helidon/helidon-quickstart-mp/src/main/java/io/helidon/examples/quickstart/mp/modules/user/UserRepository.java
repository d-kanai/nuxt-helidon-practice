package io.helidon.examples.quickstart.mp.modules.user;

import io.helidon.examples.quickstart.mp.modules.user.domain.User;
import io.helidon.examples.quickstart.mp.modules.user.dto.UserAddRequest;

public class UserRepository {
    public void addUser(User user) {
        // データベースにUserを登録するロジック
    }

    public User convertRequestToUser(UserAddRequest userAddRequest) {
        User user = new User();
        user.setName(userAddRequest.getName());
        user.setAge(userAddRequest.getAge());
        return user;
    }
}
