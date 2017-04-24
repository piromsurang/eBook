package com.example.piromsurang.ebookk.data;

import java.util.ArrayList;

/**
 * Created by Piromsurang on 4/21/2017 AD.
 */

public interface Repository {
    void loadData();
    ArrayList<Book> searchByTitle(String t);
    ArrayList<Book> searchByPublishedYear(String t);
}
