package io.helidon.examples.quickstart.mp.modules.order.internal.infra;

import io.helidon.examples.quickstart.mp.modules.order.internal.domain.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderRepository {

    List<Order> orders = new ArrayList<>();

    public Order findById(int userId) {
        return orders.stream().filter(user -> user.getId() == userId).findFirst().get();
    }

    public boolean create(Order order) {
        return orders.add(order);
    }
}
