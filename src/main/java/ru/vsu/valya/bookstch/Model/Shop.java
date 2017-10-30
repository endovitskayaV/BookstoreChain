package ru.vsu.valya.bookstch.Model;

public class Shop {
    private  int id;
    private String name;
    private String  address;
    private int chainStoreId;

    public Shop setId(int id) {
        this.id = id;
        return this;
    }

    public Shop setName(String name) {
        this.name = name;
        return this;
    }

    public Shop setAddress(String address) {
        this.address = address;
        return this;
    }

    public Shop setChainStoreId(int chainStoreId) {
        this.chainStoreId = chainStoreId;
        return this;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getChainStoreId() {
        return chainStoreId;
    }
}
