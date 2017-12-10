package ru.vsu.valya.bookstch.Model;

import java.util.Arrays;

public class ConcreteNewspaper {
    private  int id;
    private  String name;
    private int releaseYear;
    private int issue;
    private ConcreteProductShop[] ConcreteProductShopArr;

    public ConcreteNewspaper() {
        ConcreteProductShopArr =new ConcreteProductShop[100];
    }


    public int getId() {
        return id;
    }

    public ConcreteNewspaper setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ConcreteNewspaper setName(String name) {
        this.name = name;
        return this;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public ConcreteNewspaper setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
        return this;
    }

    public int getIssue() {
        return issue;
    }

    public ConcreteNewspaper setIssue(int issue) {
        this.issue = issue;
        return this;
    }

    public ConcreteProductShop[] getConcreteProductShopArr() {
        return ConcreteProductShopArr;
    }

    public ConcreteNewspaper setConcreteProductShopArr(ConcreteProductShop[] concreteProductShopArr) {
        Arrays.asList(this.ConcreteProductShopArr).addAll(Arrays.asList(ConcreteProductShopArr));
        return this;
    }
}
