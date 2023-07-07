package io.helidon.examples.quickstart.mp.order.application;

import io.helidon.examples.quickstart.mp.inventory.expose.InventoryExposeUseInventory;
import io.helidon.examples.quickstart.mp.order.domain.Order;
import io.helidon.examples.quickstart.mp.order.infra.OrderRepository;
import io.helidon.examples.quickstart.mp.user.dto.UserFindByIdResponse;
import io.helidon.examples.quickstart.mp.user.expose.UserExposeFindByIdQuery;
import io.helidon.examples.quickstart.mp.user.expose.UserStatus;

public class CreateOrderCommand {

    private final UserExposeFindByIdQuery userExposeFindByIdQuery;
    private final OrderRepository orderRepository;
    private final InventoryExposeUseInventoryInterface inventoryExposeUseInventory;

    public CreateOrderCommand() {
        //TODO: DI
        orderRepository = new OrderRepository();
        userExposeFindByIdQuery = new UserExposeFindByIdQuery();
        inventoryExposeUseInventory = new InventoryExposeUseInventory();
    }

    public boolean invoke(int userId) {
        UserFindByIdResponse userResponse = userExposeFindByIdQuery.findById(userId);
        //MEMO: 実際はこの例ではNonActiveUserはErrorレスポンスになるなどして、Enumを必要ない作りにするかもしれないが、Enumを公開することもあり得るというサンプル
        if (!userResponse.getStatus().equals(UserStatus.ACTIVE)) {
            throw new RuntimeException("Domain Rule Error?");
        }
        Order order = Order.NewOrder(userResponse.getId());
        //MEMO: 一貫性が必要な時は まとめたトランザクションにする。-----------------------
        boolean isOrderCreated = orderRepository.create(order);
        inventoryExposeUseInventory.invoke(order.getId());
        // ----------------------------------------------------------------------
        return isOrderCreated;
    }
}
