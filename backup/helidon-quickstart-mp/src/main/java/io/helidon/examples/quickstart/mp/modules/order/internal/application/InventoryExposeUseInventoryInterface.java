package io.helidon.examples.quickstart.mp.modules.order.internal.application;

import io.helidon.examples.quickstart.mp.modules.inventory.internal.dto.UseInventoryResponse;

public interface InventoryExposeUseInventoryInterface {
    UseInventoryResponse invoke(int orderId);
}
