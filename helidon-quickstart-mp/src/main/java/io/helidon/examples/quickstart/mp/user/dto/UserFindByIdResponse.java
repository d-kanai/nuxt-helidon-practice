package io.helidon.examples.quickstart.mp.user.dto;

public class UserFindByIdResponse {

    private int id;
    private String name;

    public UserFindByIdResponse(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
