package ru.vsu.valya.bookstch.Model;
public class Book {
    private  int id;
    private  String name;
    private String author;
    private  String publisher;
    private int releaseYear;
    private int pagesNumber;


    public Book setId(int id) {
        this.id = id;
        return this;
    }

    public Book setName(String name) {
        this.name = name;
        return this;
    }

    public Book setAuthor(String author) {
        this.author = author;
        return this;
    }

    public Book setPublisher(String publisher) {
        this.publisher = publisher;
        return this;
    }

    public Book setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
        return this;
    }

    public Book setPagesNumber(int pagesNumber) {
        this.pagesNumber = pagesNumber;
        return this;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public int getPagesNumber() {
        return pagesNumber;
    }

}
