package com.example.piromsurang.ebookk.data;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Created by Piromsurang on 4/29/2017 AD.
 */

public class PromotionBook extends Book {
    private ArrayList<Book> bookBundle;
    private JSONArray b;
    private double price;
    private String id;
    private String title;

    public PromotionBook(String id, String title, double price, JSONArray b) {
        super(price, "", id, title, "");
        bookBundle = new ArrayList<>();
        this.id = id;
        this.title = title;
        this.price = price;
        this.b = b;
    }

    public JSONArray getB() {
        return b;
    }

    public void addBook(Book b) {
        b.setPromotions(true);
        bookBundle.add(b);
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public ArrayList<Book> getBookBundle() {
        return bookBundle;
    }

    public String toString() {
        String output = "Title Bundle: " + title ;
        for(Book b : bookBundle) {
            output += "\nTitle: " + b.getTitle();
        }
        output += "\nPrice: " + price;
        return output;
    }
}
