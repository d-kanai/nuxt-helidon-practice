
package io.helidon.examples.quickstart.mp.modules.order.internal.presentation;

import io.helidon.examples.quickstart.mp.modules.order.internal.application.CreateOrderCommand;
import io.helidon.examples.quickstart.mp.modules.order.internal.dto.CreateOrderResponse;
import jakarta.enterprise.context.RequestScoped;
import jakarta.json.Json;
import jakarta.json.JsonBuilderFactory;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.Collections;

@Path("/order")
@RequestScoped
public class OrderResource {

    private static final JsonBuilderFactory JSON = Json.createBuilderFactory(Collections.emptyMap());
    private CreateOrderCommand createOrderCommand;

    public OrderResource() {
        //TODO: DI
        createOrderCommand = new CreateOrderCommand();
    }

    @Path("/{userId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public CreateOrderResponse createOrder(@PathParam("userId") int userId) {
        boolean isSuccess = createOrderCommand.invoke(userId);
        return new CreateOrderResponse();
    }

}

