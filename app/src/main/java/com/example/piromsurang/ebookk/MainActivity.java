package com.example.piromsurang.ebookk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.piromsurang.ebookk.data.Book;
import com.example.piromsurang.ebookk.data.MockUpBookRepository;
import com.example.piromsurang.ebookk.data.RealBookRepository;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BookView {

    BookPresenter presenter;
    RealBookRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        repository = RealBookRepository.getInstance();
        presenter = new BookPresenter(repository, this);
        repository.addObserver(presenter);
        repository.loadData();
        initializeEditText();
    }
//
//    @Override
//    public void displayList(String text) {
//        TextView textView = (TextView) findViewById(R.id.show_list_textview);
//        textView.setText(text);
//    }

    @Override
    public void displayList(ArrayList<Book> books) {
        ArrayAdapter<Book> adapter = new ArrayAdapter<Book>(this, android.R.layout.simple_list_item_1, books );
        ListView listView = (ListView) findViewById(R.id.show_list_listview);
        listView.setAdapter(adapter);
    }

    public void initializeEditText() {
        EditText editText = (EditText) findViewById(R.id.search_edit_text);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                presenter.searchByTitle(s.toString());
            }
        });
    }
}
