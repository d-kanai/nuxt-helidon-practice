package io.helidon.examples.quickstart.mp.user.dto;

//TODO All args construct + auto gettersetterで良い
public class UserFindByIdResponse {

    private int id;
    private String name;
    private String status;

    public UserFindByIdResponse() {
    }

    public UserFindByIdResponse(int id, String name, String status) {
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

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }
}
