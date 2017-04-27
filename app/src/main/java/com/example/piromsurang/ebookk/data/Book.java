package com.example.piromsurang.ebookk.data;

import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * Created by Piromsurang on 4/20/2017 AD.
 */

public class Book implements Serializable{

    private double price;
    private String img_url;
    private String id;
    private String title;
    private String pub_year;

    public Book( double price, String img_url, String id, String title, String pub_year ) {
        this.price = price;
        this.img_url = img_url;
        this.id = id;
        this.title = title;
        this.pub_year = pub_year;
    }

    public String getPub_year() { return pub_year; }

    public double getPrice() {
        return price;
    }

    public String getImg_url() {
        return img_url;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setImg_url(String url) {
        this.img_url = url;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPub_year(String pub_year) { this.pub_year = pub_year; }

    public String toString() {
        return "Title: " + title + "\nPublished Year: " + pub_year + "\nPrice: " + price + "\n";
    }

}
