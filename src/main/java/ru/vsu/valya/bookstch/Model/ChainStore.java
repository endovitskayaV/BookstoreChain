package ru.vsu.valya.bookstch.Model;

public class ChainStore {
    private  int id;
    private  String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ChainStore setId(int id) {
        this.id = id;
        return this;
    }

    public ChainStore setName(String name) {
        this.name = name;
        return this;
    }
}
