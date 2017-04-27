package com.example.piromsurang.ebookk;

import com.example.piromsurang.ebookk.data.Book;

import java.util.ArrayList;

/**
 * Created by Piromsurang on 4/27/2017 AD.
 */

public class Cart {
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
}
