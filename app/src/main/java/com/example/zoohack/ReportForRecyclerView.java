package com.example.zoohack;

public class ReportForRecyclerView {

    private String name;
    private String place;
    private String author;
    private String rating;
    private String affected;
    private String num;

    public ReportForRecyclerView(String name, String place, String author, String rating,String affected, String num){

        this.name=name;
        this.place = place;
        this.author = author;
        this.rating = rating;
        this.affected = affected;
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getAffected() {
        return affected;
    }

    public void setAffected(String affected) {
        this.affected = affected;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

}