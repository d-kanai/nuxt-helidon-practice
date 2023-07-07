package io.helidon.examples.quickstart.mp.order.application;

import io.helidon.examples.quickstart.mp.inventory.dto.UseInventoryResponse;
import io.helidon.examples.quickstart.mp.user.domain.User;
import io.helidon.examples.quickstart.mp.user.dto.UserFindByIdResponse;

public interface UserExposeFindByIdQueryInterface {
    UserFindByIdResponse invoke(int userId);
}


