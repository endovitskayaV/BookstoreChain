package ru.vsu.valya.bookstch.Model;

public class Magazine {
    private  int id;
    private  String name;
    private int releaseYear;
    private int issue;
    private int pagesNumber;

    public Magazine setId(int id) {
        this.id = id;
        return this;
    }

    public Magazine setName(String name) {
        this.name = name;
        return this;
    }

    public Magazine setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
        return this;
    }

    public Magazine setIssue(int issue) {
        this.issue = issue;
        return this;
    }

    public Magazine setPagesNumber(int pagesNumber) {
        this.pagesNumber = pagesNumber;
        return this;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public int getIssue() {
        return issue;
    }

    public int getPagesNumber() {
        return pagesNumber;
    }
}
