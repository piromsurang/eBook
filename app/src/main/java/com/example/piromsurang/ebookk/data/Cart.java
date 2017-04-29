package com.example.piromsurang.ebookk.data;

import android.os.CountDownTimer;

import com.example.piromsurang.ebookk.data.Book;
import com.example.piromsurang.ebookk.data.PromotionBook;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Piromsurang on 4/27/2017 AD.
 */

public class Cart implements Serializable {

    ArrayList<Book> selectedBooks;
    ArrayList<Book> history;

    public Cart() {
        selectedBooks = new ArrayList<>();
        history = new ArrayList<>();
    }

    public void addCart(Book b) {
        selectedBooks.add(b);
    }

    public void removeBook(Book b) {
        selectedBooks.remove(b);
    }

    public void clearCart() {
        history.addAll(selectedBooks);
        selectedBooks.clear();
        countDownRefundable();
    }

    public void countDownRefundable() {
        for(final Book b : history ) {
            b.setRefundable(true);
            new CountDownTimer(300000, 1000) {

                public void onTick(long millisUntilFinished) {

                }

                public void onFinish() {
                    b.setRefundable(false);
                }
            }.start();
        }
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

    public void refundBook(Book b) {
        history.remove(b);
    }

    public ArrayList<Book> getHistory() {
        return history;
    }
}
