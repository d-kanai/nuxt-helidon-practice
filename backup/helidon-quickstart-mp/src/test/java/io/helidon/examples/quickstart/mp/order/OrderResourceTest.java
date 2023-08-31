package io.helidon.examples.quickstart.mp.order;

import io.helidon.examples.quickstart.mp.modules.inventory.internal.domain.Inventory;
import io.helidon.examples.quickstart.mp.modules.inventory.internal.infra.InventoryRepository;
import io.helidon.examples.quickstart.mp.modules.order.internal.dto.CreateOrderResponse;
import io.helidon.examples.quickstart.mp.modules.user.internal.domain.User;
import io.helidon.examples.quickstart.mp.modules.user.expose.UserStatus;
import io.helidon.examples.quickstart.mp.modules.user.internal.infra.UserRepository;
import io.helidon.microprofile.tests.junit5.HelidonTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.client.WebTarget;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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
