package io.helidon.examples.quickstart.mp.order;

import io.helidon.examples.quickstart.mp.order.dto.CreateOrderResponse;
import io.helidon.examples.quickstart.mp.user.domain.User;
import io.helidon.examples.quickstart.mp.user.dto.UserFindByIdResponse;
import io.helidon.examples.quickstart.mp.user.expose.UserStatus;
import io.helidon.examples.quickstart.mp.user.infra.UserRepository;
import io.helidon.microprofile.tests.junit5.HelidonTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.client.WebTarget;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.fail;

@HelidonTest
class OrderResourceTest {

    @Inject
    private WebTarget target;

    @Test
    void createOrder() {
        //given
        String userId = "1";
        // user1はActive user
        //when
        CreateOrderResponse response = target.path("order/" + userId).request().get(CreateOrderResponse.class);
        //then
        assertThat(response, is(CreateOrderResponse.class));
    }

    @Test
    void createOrderFail() {
        //given
        String userId = "2";
        // user2はNon Active user
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
