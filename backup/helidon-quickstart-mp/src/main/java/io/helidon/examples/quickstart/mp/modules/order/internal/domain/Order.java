package io.helidon.examples.quickstart.mp.modules.order.internal.domain;

public class Order {

    private int id;
    private int userId;



    public static Order NewOrder(int userId) {
        //TODO: generate UUID;
        return new Order(1, userId);

    }

    public Order(int id, int userId) {
        this.id = id;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }


}
