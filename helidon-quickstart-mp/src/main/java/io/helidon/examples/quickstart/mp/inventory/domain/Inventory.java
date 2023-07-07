package io.helidon.examples.quickstart.mp.inventory.domain;

public class Inventory {

    private int id;
    private Status status;


    public Inventory(int id, Status status) {
        this.id = id;
        this.status = status;
    }

    public Status getStatus() {
        return this.status;
    }

    public void reserve() {
        this.status = Status.RESERVED;
    }

    public enum Status {
        NEW,
        RESERVED
    }

    public int getId() {
        return id;
    }



}
