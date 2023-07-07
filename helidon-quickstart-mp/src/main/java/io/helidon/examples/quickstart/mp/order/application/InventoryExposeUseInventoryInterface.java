package io.helidon.examples.quickstart.mp.order.application;

import io.helidon.examples.quickstart.mp.inventory.dto.UseInventoryResponse;

public interface InventoryExposeUseInventoryInterface {
    UseInventoryResponse invoke(int orderId);
}
