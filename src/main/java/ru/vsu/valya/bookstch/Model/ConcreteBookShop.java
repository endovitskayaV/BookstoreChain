package ru.vsu.valya.bookstch.Model;

public class ConcreteBookShop extends  ConcreteProductShop{
    private int bookId;
    private  int shopId;
    private  int price;
    private  int copiesNumber;

    public ConcreteBookShop(){}

    public ConcreteBookShop(int bookId, int shopId, int price, int copiesNumber) {
        this.bookId = bookId;
        this.shopId = shopId;
        this.price = price;
        this.copiesNumber = copiesNumber;
    }

    public  ConcreteBookShop setBookId(int bookId) {
        this.bookId = bookId;
        return this;
    }

    public  ConcreteBookShop setShopId(int shopId) {
        this.shopId = shopId;
        return this;
    }

    public ConcreteBookShop setPrice(int price) {
        this.price = price;
        return this;
    }

    public ConcreteBookShop setCopiesNumber(int copiesNumber) {
        this.copiesNumber = copiesNumber;
        return this;
    }

    public int getBookId() {
        return bookId;
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
