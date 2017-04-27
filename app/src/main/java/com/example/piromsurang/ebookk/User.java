package com.example.piromsurang.ebookk;

/**
 * Created by Piromsurang on 4/27/2017 AD.
 */

public class User {
    private String id;
    private String name;
    private double money;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
        money = 0;
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

}
