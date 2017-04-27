package com.example.piromsurang.ebookk;

import com.example.piromsurang.ebookk.data.Book;

/**
 * Created by Piromsurang on 4/27/2017 AD.
 */

public class User {
    private String id;
    private String name;
    private double money;
    private Cart cart;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
        money = 0;
        cart = new Cart();
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String id) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getMoney() {
        return money;
    }

    public void addMoney(double amount) {
        money += amount;
    }

    public boolean pay(double amount) {
        if( money - amount < 0 ) {
            return false;
        } else {
            money -= amount;
            return true;
        }
    }

    public void addToCart(Book b) {
        cart.addCart(b);
    }

    public void removeFromCart(Book b) {
        cart.removeBook(b);
    }

    public void emptyCart() {
        cart.clearCart();
    }
}
