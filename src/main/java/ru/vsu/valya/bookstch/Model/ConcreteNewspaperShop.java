package ru.vsu.valya.bookstch.Model;

public class ConcreteNewspaperShop extends  ConcreteProductShop {
    private int newspaperId;
    private  int shopId;
    private  int price;
    private  int copiesNumber;

    public ConcreteNewspaperShop setNewspaperId(int newspaperId) {
        this.newspaperId = newspaperId;
        return this;
    }

    public ConcreteNewspaperShop setShopId(int shopId) {
        this.shopId = shopId;
        return this;
    }

    public ConcreteNewspaperShop setPrice(int price) {
        this.price = price;
        return this;
    }

    public ConcreteNewspaperShop setCopiesNumber(int copiesNumber) {
        this.copiesNumber = copiesNumber;
        return this;
    }

    public int getNewspaperId() {
        return newspaperId;
    }

    public int getShopId() {
        return shopId;
    }

    public int getPrice() {
        return price;
    }

    public int getCopiesNumber() {
        return copiesNumber;
    }
}
