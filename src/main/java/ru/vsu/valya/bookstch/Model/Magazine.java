package ru.vsu.valya.bookstch.Model;

import java.util.Arrays;

public class Magazine {
    private  int id;
    private  String name;
    private int releaseYear;
    private int issue;
    private int pagesNumber;
    private ConcreteProductInShop[] concreteProductInShopArr;

    public boolean getIsAvailable() {
        return isAvailable;
    }

    public Magazine setAvailable(boolean available) {
        isAvailable = available;
        return this;
    }

    private boolean isAvailable;
    public Magazine() {
        concreteProductInShopArr =new ConcreteProductInShop[100];
    }

    public int getId() {
        return id;
    }

    public Magazine setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Magazine setName(String name) {
        this.name = name;
        return this;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public Magazine setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
        return this;
    }

    public int getIssue() {
        return issue;
    }

    public Magazine setIssue(int issue) {
        this.issue = issue;
        return this;
    }

    public int getPagesNumber() {
        return pagesNumber;
    }

    public Magazine setPagesNumber(int pagesNumber) {
        this.pagesNumber = pagesNumber;
        return this;
    }

    public ConcreteProductInShop[] getConcreteProductInShopArr() {
        return concreteProductInShopArr;
    }

    public Magazine setConcreteProductInShopArr(ConcreteProductInShop[] concreteProductInShopArr) {
        Arrays.asList(this.concreteProductInShopArr).addAll(Arrays.asList(this.concreteProductInShopArr));
        return this;
    }
}
