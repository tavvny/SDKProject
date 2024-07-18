package com.example.condencedgeek.Model;

public class Courses {

    int id, category ;
    String img, title, descrip, price, text ;

    public Courses(int id, String img, String title, String descrip, String price, String text, int category ) {
        this.id = id;
        this.img = img;
        this.title = title;
        this.descrip = descrip;
        this.price = price;
        this.text = text;
        this.category = category;

    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getdescrip() {
        return descrip;
    }

    public void setdescrip(String descrip) {
        this.descrip = descrip;
    }

    public String getprice() {
        return price;
    }

    public void setprice(String price) {
        this.price = price;
    }


}
