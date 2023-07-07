package io.helidon.examples.quickstart.mp.inventory.dto;

//TODO All args construct + auto gettersetterで良い
public class UseInventoryResponse {


    private boolean isSuccess;

    public UseInventoryResponse(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

}
