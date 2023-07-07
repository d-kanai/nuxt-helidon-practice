
package io.helidon.examples.quickstart.mp.user.expose;

import io.helidon.examples.quickstart.mp.user.application.UserFindByIdQuery;
import io.helidon.examples.quickstart.mp.user.domain.User;
import io.helidon.examples.quickstart.mp.user.dto.UserFindByIdResponse;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class UserExposeFindByIdQuery {

    private UserFindByIdQuery userFindByIdQuery;

    public UserExposeFindByIdQuery() {
        //TODO: DI
        userFindByIdQuery = new UserFindByIdQuery();
    }

    public UserFindByIdResponse findById(int userId) {
        User user = userFindByIdQuery.invoke(userId);
        //TODO: mapping いい感じにする
        return new UserFindByIdResponse(user.getId(), user.getName(), user.getStatus().toString());
    }

}

