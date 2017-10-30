package ru.vsu.valya.bookstch.Model;

import java.util.Date;

public class Newspaper {
    private  int id;
    private  String name;
    private Date releaseDate;
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

    public Date getReleaseDate() {
        return releaseDate;
    }

    public Newspaper setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
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
