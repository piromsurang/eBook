package com.example.piromsurang.ebookk;

import com.example.piromsurang.ebookk.data.Book;

import java.util.ArrayList;

/**
 * Created by Piromsurang on 4/21/2017 AD.
 */

public interface BookView {
    void displayList(ArrayList<Book> books);
    void createDialog(int b);
}
