package ru.vsu.valya.bookstch.Model;

import java.util.Arrays;

public class Book {
    private  int id;
    private  String name;
    private String author;
    private  String publisher;
    private int releaseYear;
    private int pagesNumber;
    private ConcreteProductInShop[] concreteProductInShopArr;

   public boolean getIsAvailable() {
        return isAvailable;
    }

    public Book setAvailable(boolean available) {
        isAvailable = available;
        return this;
    }

    private boolean isAvailable;

    public Book(int id, String name, String author, String publisher,
                int releaseYear, int pagesNumber, ConcreteProductInShop[] concreteProductInShopArr) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.releaseYear = releaseYear;
        this.pagesNumber = pagesNumber;
        setConcreteProductInShopArr(concreteProductInShopArr);
    }

    public Book() {
        concreteProductInShopArr =new ConcreteProductInShop[100];
    }

    public int getId() {
        return id;
    }

    public Book setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Book setName(String name) {
        this.name = name;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public Book setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getPublisher() {
        return publisher;
    }

    public Book setPublisher(String publisher) {
        this.publisher = publisher;
        return this;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public Book setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
        return this;
    }

    public int getPagesNumber() {
        return pagesNumber;
    }

    public Book setPagesNumber(int pagesNumber) {
        this.pagesNumber = pagesNumber;
        return this;
    }

    public ConcreteProductInShop[] getConcreteProductInShopArr() {
        return concreteProductInShopArr;
    }

    public Book setConcreteProductInShopArr(ConcreteProductInShop[] concreteProductInShopArr) {
       Arrays.asList(this.concreteProductInShopArr).addAll(Arrays.asList(concreteProductInShopArr));
        return this;
    }
}
