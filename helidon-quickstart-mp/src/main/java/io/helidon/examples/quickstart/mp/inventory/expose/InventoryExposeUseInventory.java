
package io.helidon.examples.quickstart.mp.inventory.expose;

import io.helidon.examples.quickstart.mp.inventory.application.UseInventoryCommand;
import io.helidon.examples.quickstart.mp.inventory.dto.UseInventoryResponse;
import io.helidon.examples.quickstart.mp.user.application.UserFindByIdQuery;
import io.helidon.examples.quickstart.mp.user.domain.User;
import io.helidon.examples.quickstart.mp.user.dto.UserFindByIdResponse;

public class InventoryExposeUseInventory {

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

