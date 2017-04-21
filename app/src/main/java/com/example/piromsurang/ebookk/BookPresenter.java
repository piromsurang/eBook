package com.example.piromsurang.ebookk;

import android.widget.ArrayAdapter;

import com.example.piromsurang.ebookk.data.Book;
import com.example.piromsurang.ebookk.data.MockUpBookRepository;
import com.example.piromsurang.ebookk.data.RealBookRepository;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Piromsurang on 4/21/2017 AD.
 */

public class BookPresenter implements Observer {

    ArrayList<Book> books;
    RealBookRepository repository;
    BookView view;

    public BookPresenter( RealBookRepository repository, BookView view ) {
        this.repository = repository;
        this.view = view;
    }


    public void displayList(ArrayList<Book> b) {
        view.displayList(b);
    }

//    public String getInfo() {
//        books = repository.getBookList();
//        String text = "";
//        for( int i = 0 ; i < books.size() ; i++ ) {
//            text += "Title: " + books.get(i).getTitle() + " Price: " + books.get(i).getPrice() + "\n";
//        }
//        return text;
//    }

    public void searchByTitle(String t) {
        if( t.length() == 0 ) {
            view.displayList(repository.getBookList());
        }
        else {
            view.displayList(repository.searchByTitle(t));
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        displayList(repository.getBookList());
        System.out.println("updating data...");
    }
}
