package io.helidon.examples.quickstart.mp.inventory.infra;

import io.helidon.examples.quickstart.mp.inventory.domain.Inventory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
