package io.helidon.examples.quickstart.mp.modules.user.internal.domain;

import io.helidon.examples.quickstart.mp.modules.user.expose.UserStatus;

public class User {

    private int id;
    private String name;
    private UserStatus status;

    public UserStatus getStatus() {
        return this.status;
    }

    public User(int id, String name, UserStatus status) {
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
