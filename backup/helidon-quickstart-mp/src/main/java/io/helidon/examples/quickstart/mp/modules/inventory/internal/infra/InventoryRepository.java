package io.helidon.examples.quickstart.mp.modules.inventory.internal.infra;

import io.helidon.examples.quickstart.mp.modules.inventory.internal.domain.Inventory;

import java.util.ArrayList;
import java.util.List;

public class InventoryRepository {

    public static List<Inventory> inventories =  new ArrayList<>();

    public void save(Inventory inventory) {
        //TODO: とりあえず全入れ替え
        InventoryRepository.inventories.remove(inventory);
        InventoryRepository.inventories.add(inventory);
    }

    public Inventory findFirst() {
        return inventories.stream().findFirst().get();
    }
}
