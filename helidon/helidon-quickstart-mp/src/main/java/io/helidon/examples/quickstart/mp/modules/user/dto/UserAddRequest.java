package io.helidon.examples.quickstart.mp.modules.user.dto;

public class UserAddRequest {
    private String name;
    private int age;

    public UserAddRequest(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}