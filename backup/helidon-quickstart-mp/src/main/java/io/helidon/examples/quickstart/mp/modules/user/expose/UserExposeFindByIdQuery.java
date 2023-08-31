
package io.helidon.examples.quickstart.mp.modules.user.expose;

import io.helidon.examples.quickstart.mp.modules.order.internal.application.UserExposeFindByIdQueryInterface;
import io.helidon.examples.quickstart.mp.modules.user.internal.application.UserFindByIdQuery;
import io.helidon.examples.quickstart.mp.modules.user.internal.domain.User;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class UserExposeFindByIdQuery implements UserExposeFindByIdQueryInterface {

    private UserFindByIdQuery userFindByIdQuery;

    public UserExposeFindByIdQuery() {
        //TODO: DI
        userFindByIdQuery = new UserFindByIdQuery();
    }

    public UserExposeFindByIdQueryResponse invoke(int userId) {
        User user = userFindByIdQuery.invoke(userId);
        //TODO: mapping いい感じにする
        return new UserExposeFindByIdQueryResponse(user.getId(), user.getName(), user.getStatus());
    }

}

