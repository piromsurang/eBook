package com.example.piromsurang.ebookk;

import com.example.piromsurang.ebookk.data.Book;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Piromsurang on 4/27/2017 AD.
 */

public class Cart implements Serializable {

    ArrayList<Book> selectedBooks;

    public Cart() {
        selectedBooks = new ArrayList<>();
    }

    public void addCart(Book b) {
        selectedBooks.add(b);
    }

    public void removeBook(Book b) {
        selectedBooks.remove(b);
    }

    public void clearCart() {
        selectedBooks.clear();
    }

    public ArrayList<Book> getSelectedBooks() {
        return selectedBooks;
    }

    public double getTotal() {
        double sum = 0;
        for(Book b : selectedBooks) {
            sum += b.getPrice();
        }
        return sum;
    }
}
