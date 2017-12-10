package ru.vsu.valya.bookstch.Model;


public class Newspaper {
    private  int id;
    private  String name;
    private int releaseYear;
    private int issue;

    public int getId() {
        return id;
    }

    public Newspaper setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Newspaper setName(String name) {
        this.name = name;
        return this;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public Newspaper setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
        return this;
    }

    public int getIssue() {
        return issue;
    }

    public Newspaper setIssue(int issue) {
        this.issue = issue;
        return this;
    }
}
