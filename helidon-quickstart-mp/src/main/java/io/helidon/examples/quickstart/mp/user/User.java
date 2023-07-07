package io.helidon.examples.quickstart.mp.user;

public class User {

    private int id;
    private String name;
    private STATUS status;

    public STATUS getStatus() {
        return this.status;
    }

    public enum STATUS {
        ACTIVE,
        DISABLE,
    }

    public User(int id, String name, STATUS status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }


}
