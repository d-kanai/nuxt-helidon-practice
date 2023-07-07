package io.helidon.examples.quickstart.mp.order.application;

import io.helidon.examples.quickstart.mp.order.domain.Order;
import io.helidon.examples.quickstart.mp.order.infra.OrderRepository;
import io.helidon.examples.quickstart.mp.user.dto.UserFindByIdResponse;
import io.helidon.examples.quickstart.mp.user.expose.UserExposeFindByIdQuery;

public class CreateOrderCommand {

    private final UserExposeFindByIdQuery userExposeFindByIdQuery;
    private final OrderRepository orderRepository;

    public CreateOrderCommand() {
        //TODO: DI
        orderRepository = new OrderRepository();
        userExposeFindByIdQuery = new UserExposeFindByIdQuery();
    }

    public boolean invoke(int userId) {
        UserFindByIdResponse userResponse = userExposeFindByIdQuery.findById(userId);
        //TODO: 別境界のENUMどうする・・・
        if (!userResponse.getStatus().equals("ACTIVE")) {
            throw new RuntimeException("domain rule error");
        }
        Order order = Order.NewOrder(userResponse.getId());
        return orderRepository.create(order);
    }
}
