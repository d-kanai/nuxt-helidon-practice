
package io.helidon.examples.quickstart.mp.modules.inventory.expose;

import io.helidon.examples.quickstart.mp.modules.inventory.internal.application.UseInventoryCommand;
import io.helidon.examples.quickstart.mp.modules.inventory.internal.dto.UseInventoryResponse;
import io.helidon.examples.quickstart.mp.modules.order.internal.application.InventoryExposeUseInventoryInterface;

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

