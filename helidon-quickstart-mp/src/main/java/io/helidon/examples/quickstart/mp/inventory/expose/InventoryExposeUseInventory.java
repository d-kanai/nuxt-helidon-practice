
package io.helidon.examples.quickstart.mp.inventory.expose;

import io.helidon.examples.quickstart.mp.inventory.application.UseInventoryCommand;
import io.helidon.examples.quickstart.mp.inventory.dto.UseInventoryResponse;
import io.helidon.examples.quickstart.mp.order.application.InventoryExposeUseInventoryInterface;

public class InventoryExposeUseInventory implements InventoryExposeUseInventoryInterface {

    private UseInventoryCommand useInventoryCommand;

    public InventoryExposeUseInventory() {
        //TODO: DI
        useInventoryCommand = new UseInventoryCommand();
    }

    public UseInventoryResponse invoke(int userId) {
        boolean isSuccess = useInventoryCommand.invoke(userId);
        return new UseInventoryResponse(isSuccess);
    }

}

