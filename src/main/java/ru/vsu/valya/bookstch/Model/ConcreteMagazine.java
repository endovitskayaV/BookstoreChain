package ru.vsu.valya.bookstch.Model;

import java.util.Arrays;

public class ConcreteMagazine {
    private  int id;
    private  String name;
    private int releaseYear;
    private int issue;
    private int pagesNumber;
    private ConcreteProductShop[] ConcreteProductShopArr;

    public ConcreteMagazine() {
        ConcreteProductShopArr =new ConcreteProductShop[100];
    }

    public int getId() {
        return id;
    }

    public ConcreteMagazine setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ConcreteMagazine setName(String name) {
        this.name = name;
        return this;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public ConcreteMagazine setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
        return this;
    }

    public int getIssue() {
        return issue;
    }

    public ConcreteMagazine setIssue(int issue) {
        this.issue = issue;
        return this;
    }

    public int getPagesNumber() {
        return pagesNumber;
    }

    public ConcreteMagazine setPagesNumber(int pagesNumber) {
        this.pagesNumber = pagesNumber;
        return this;
    }

    public ConcreteProductShop[] getConcreteProductShopArr() {
        return ConcreteProductShopArr;
    }

    public ConcreteMagazine setConcreteProductShopArr(ConcreteProductShop[] concreteProductShopArr) {
        Arrays.asList(this.ConcreteProductShopArr).addAll(Arrays.asList(ConcreteProductShopArr));
        return this;
    }
}
