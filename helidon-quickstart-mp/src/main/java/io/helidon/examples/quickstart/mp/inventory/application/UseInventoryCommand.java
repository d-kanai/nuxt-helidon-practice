package io.helidon.examples.quickstart.mp.inventory.application;

import io.helidon.examples.quickstart.mp.inventory.domain.Inventory;
import io.helidon.examples.quickstart.mp.inventory.infra.InventoryRepository;

public class UseInventoryCommand {

    private final InventoryRepository inventoryRepository;

    public UseInventoryCommand() {
        //TODO: DI
        inventoryRepository = new InventoryRepository();
    }

    public boolean invoke(int orderId) {
        Inventory inventory = inventoryRepository.findFirst();
        inventory.reserve();
        inventoryRepository.save(inventory);
        return true;
    }
}
