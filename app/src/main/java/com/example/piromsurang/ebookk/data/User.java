package com.example.piromsurang.ebookk.data;

import java.io.Serializable;

/**
 * Created by Piromsurang on 4/27/2017 AD.
 */

public class User implements Serializable {
    private long serialVersionUID = 10L;
    private String id;
    private String name;
    private double money;
    private Cart cart;

    private static User instance;

    public static User getInstance() {
        if(instance == null) {
            instance = new User("10", "Gift");
        }
        return instance;
    }

    private User(String id, String name) {
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

    public Cart getCart() {
        return cart;
    }

    public int refundBook(Book b) {
        if( b.isRefundable() ) {
            cart.refundBook(b);
            money += b.getPrice();
            return 1;
        } else {
            return 2;
        }
    }
}
