package com.example.lenovo.test2;

/**
 * Created by lenovo on 2016/7/12.
 */
public class menu {
    String id;
    String name;


    String img;
    double price;
    resource res;
    String discription;
    String author;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setRes(resource res) {
        this.res = res;
    }

    public resource getRes() {
        return res;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getDiscription() {
        return discription;
    }
}
