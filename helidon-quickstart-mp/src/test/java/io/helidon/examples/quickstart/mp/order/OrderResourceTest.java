package io.helidon.examples.quickstart.mp.order;

import io.helidon.examples.quickstart.mp.inventory.domain.Inventory;
import io.helidon.examples.quickstart.mp.inventory.infra.InventoryRepository;
import io.helidon.examples.quickstart.mp.order.dto.CreateOrderResponse;
import io.helidon.examples.quickstart.mp.order.infra.OrderRepository;
import io.helidon.examples.quickstart.mp.user.domain.User;
import io.helidon.examples.quickstart.mp.user.expose.UserStatus;
import io.helidon.examples.quickstart.mp.user.infra.UserRepository;
import io.helidon.microprofile.tests.junit5.HelidonTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.client.WebTarget;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.fail;

@HelidonTest
class OrderResourceTest {

    @Inject
    private WebTarget target;

    @BeforeEach
    void setup() {
        UserRepository.users = new ArrayList<>();
        InventoryRepository.inventories = new ArrayList<>();
    }

    @Test
    void 発注をして在庫を予約する() {
        //given
        int userId = 1;
        UserRepository.users.add(new User(userId, "daiki", UserStatus.ACTIVE));
        InventoryRepository.inventories.add(new Inventory(1, Inventory.Status.NEW));
        // user1はActive user
        //when
        CreateOrderResponse response = target.path("order/" + userId).request().get(CreateOrderResponse.class);
        //then
        assertThat(response, is(CreateOrderResponse.class));
        assertThat(InventoryRepository.inventories.get(0).getStatus(), is(Inventory.Status.RESERVED));
    }

    @Test
    void 注文者のStatusがDISABLEだと発注できない() {
        //given
        int userId = 1;
        UserRepository.users.add(new User(userId, "daiki", UserStatus.DISABLE));
        //when
        try {
            target.path("order/" + userId).request().get(CreateOrderResponse.class);
        } catch (RuntimeException e) {
            return;
        }
        //then
        fail("Exceptionがスローされていない");
    }

}
