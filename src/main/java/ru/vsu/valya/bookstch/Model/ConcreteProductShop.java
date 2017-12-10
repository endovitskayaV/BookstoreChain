package ru.vsu.valya.bookstch.Model;

public class ConcreteProductShop {

    private String itemName;
    private int itemId;
    private  int shopId;
    private  int price;
    private  int copiesNumber;

    public String getItemName() {
        return itemName;
    }

    public ConcreteProductShop setItemName(String itemName) {
        this.itemName = itemName;
        return this;
    }

   public int getItemId() {
        return itemId;
    }

   public ConcreteProductShop setItemId(int itemId) {
        this.itemId = itemId;
        return this;
    }

   public int getShopId() {
        return shopId;
    }

   public ConcreteProductShop setShopId(int shopId) {
        this.shopId = shopId;
        return this;
    }

   public int getPrice() {
        return price;
    }

   public ConcreteProductShop setPrice(int price) {
        this.price = price;
        return this;
    }

   public int getCopiesNumber() {
        return copiesNumber;
    }

   public ConcreteProductShop setCopiesNumber(int copiesNumber) {
        this.copiesNumber = copiesNumber;
        return this;
    }

}
