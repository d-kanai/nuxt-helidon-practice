package io.helidon.examples.quickstart.mp.modules.user.internal.dto;

import io.helidon.examples.quickstart.mp.modules.user.expose.UserStatus;

//TODO All args construct + auto gettersetterで良い
public class UserFindByIdResponse {

    private int id;
    private String name;
    private UserStatus status;

    public UserFindByIdResponse() {
    }

    public UserFindByIdResponse(int id, String name, UserStatus status) {
        this.id = id;
        this.name = name;
        //TODO: enum変換どこが良いか
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public UserStatus getStatus() {
        return this.status;
    }
}
