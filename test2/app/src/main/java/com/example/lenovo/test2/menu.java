package com.example.lenovo.test2;

/**
 * Created by lenovo on 2016/7/12.
 */
public class menu {
    String id;
    String name;
    String img;
    double price;
//    resource res;
    String discription;
    String type;
    Double score;
    Double del;

    public menu(String id, String name, String img, double price, String discription, String type, Double score, Double del) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.price = price;
        this.discription = discription;
        this.type = type;
        this.score = score;
        this.del = del;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }


//    public String getAuthor() {
//        return author;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Double getDel() {
        return del;
    }

    public void setDel(Double del) {
        this.del = del;
    }

//    }
//
//    public void setAuthor(String author) {
//        this.author = author;
//    }

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

//    public void setRes(resource res) {
//        this.res = res;
//    }
//
//    public resource getRes() {
//        return res;
//    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getDiscription() {
        return discription;
    }
}
