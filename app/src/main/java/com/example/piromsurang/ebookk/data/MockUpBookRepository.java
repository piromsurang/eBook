package com.example.piromsurang.ebookk.data;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by Piromsurang on 4/21/2017 AD.
 */

public class MockUpBookRepository extends Observable implements Repository {

    private ArrayList<Book> books;
    private static MockUpBookRepository instance;

    public MockUpBookRepository() {
        books = new ArrayList<Book>();
    }

    public static MockUpBookRepository getInstance() {
        if(instance == null) {
            instance = new MockUpBookRepository();
        }

        return instance;
    }

    public ArrayList<Book> getBookList() {
        return books;
    }

    @Override
    public void loadData() {
        System.out.println("Start downloading data...");
        books.add(new Book( 20, "a", "a", "a", "2000"));
        books.add(new Book( 10, "b", "b", "b", "2001"));
        books.add(new Book( 30, "c", "c", "c", "2002"));
        System.out.println("Finish downloading data...");
        setChanged();
        notifyObservers();
    }
}
