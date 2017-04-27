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

    private ArrayList<Book> books;
    private RealBookRepository repository;
    private BookView view;
    private int checkRadioButton;
    private final int SEARCH_BY_TITLE = 1;
    private final int SEARCH_BY_PUBYEAR = 2;

    public BookPresenter( RealBookRepository repository, BookView view ) {
        this.repository = repository;
        this.view = view;
        checkRadioButton = SEARCH_BY_TITLE;
    }


    public void displayList(ArrayList<Book> b) {
        view.displayList(b);
    }

    public void searchByTitle(String t) {
        if( t.length() == 0 ) {
            view.displayList(repository.getBookList());
        }
        else {
            view.displayList(repository.searchByTitle(t));
        }
    }

    public void searchByPublishedYear(String t ) {
        if( t.length() == 0 ) {
            view.displayList(repository.getBookList());
        } else {
            view.displayList(repository.searchByPublishedYear(t));
        }
    }

    public void searchBy(String t) {
        if( checkRadioButton == SEARCH_BY_TITLE ) {
            searchByTitle(t);
        } else if( checkRadioButton == SEARCH_BY_PUBYEAR ) {
            searchByPublishedYear(t);
        }
    }

    public void onCheckRadioButton(int checked) {
        checkRadioButton = checked;
    }

    @Override
    public void update(Observable o, Object arg) {
        displayList(repository.getBookList());
        System.out.println("updating data...");
    }
}
