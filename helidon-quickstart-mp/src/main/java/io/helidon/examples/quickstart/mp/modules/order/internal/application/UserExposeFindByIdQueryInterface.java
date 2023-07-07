package io.helidon.examples.quickstart.mp.modules.order.internal.application;

import io.helidon.examples.quickstart.mp.modules.user.expose.UserExposeFindByIdQueryResponse;

public interface UserExposeFindByIdQueryInterface {
    UserExposeFindByIdQueryResponse invoke(int userId);
}


