package ru.vsu.valya.bookstch.Model;

public class ConcreteProductInShop {

    private String itemName;
    private int itemId;
    private  int shopId;
    private  int price;
    private  int copiesNumber;

    public String getItemName() {
        return itemName;
    }

    public ConcreteProductInShop setItemName(String itemName) {
        this.itemName = itemName;
        return this;
    }

   public int getItemId() {
        return itemId;
    }

   public ConcreteProductInShop setItemId(int itemId) {
        this.itemId = itemId;
        return this;
    }

   public int getShopId() {
        return shopId;
    }

   public ConcreteProductInShop setShopId(int shopId) {
        this.shopId = shopId;
        return this;
    }

   public int getPrice() {
        return price;
    }

   public ConcreteProductInShop setPrice(int price) {
        this.price = price;
        return this;
    }

   public int getCopiesNumber() {
        return copiesNumber;
    }

   public ConcreteProductInShop setCopiesNumber(int copiesNumber) {
        this.copiesNumber = copiesNumber;
        return this;
    }

}
