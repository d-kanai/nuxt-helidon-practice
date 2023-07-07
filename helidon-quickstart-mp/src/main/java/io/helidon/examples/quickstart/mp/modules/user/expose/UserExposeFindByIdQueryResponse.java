package io.helidon.examples.quickstart.mp.modules.user.expose;

public class UserExposeFindByIdQueryResponse {
    private final int id;
    private final String name;
    private final UserStatus status;

    public UserExposeFindByIdQueryResponse(int id, String name, UserStatus status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public UserStatus getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
