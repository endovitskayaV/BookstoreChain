package ru.vsu.valya.bookstch.Model;

public class ConcreteMagazineShop extends  ConcreteProductShop {
    private int magazineId;
    private  int shopId;
    private  int price;
    private  int copiesNumber;

    public ConcreteMagazineShop setMagazineId(int magazineId) {
        this.magazineId = magazineId;
        return this;
    }

    public ConcreteMagazineShop setShopId(int shopId) {
        this.shopId = shopId;
        return this;
    }

    public ConcreteMagazineShop setPrice(int price) {
        this.price = price;
        return this;
    }

    public ConcreteMagazineShop setCopiesNumber(int copiesNumber) {
        this.copiesNumber = copiesNumber;
        return this;
    }

    public int getMagazineId() {
        return magazineId;
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
