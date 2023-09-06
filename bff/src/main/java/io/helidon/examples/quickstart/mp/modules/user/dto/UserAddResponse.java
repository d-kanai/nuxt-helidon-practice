package io.helidon.examples.quickstart.mp.modules.user.dto;

public class UserAddResponse {
    private String message;

    public UserAddResponse() {}

    public UserAddResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    // getter
}



