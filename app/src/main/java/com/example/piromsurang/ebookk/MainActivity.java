package com.example.piromsurang.ebookk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.piromsurang.ebookk.data.Book;
import com.example.piromsurang.ebookk.data.MockUpBookRepository;
import com.example.piromsurang.ebookk.data.RealBookRepository;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BookView {

    private BookPresenter presenter;
    private RealBookRepository repository;
    private final int SEARCH_BY_TITLE = 1;
    private final int SEARCH_BY_PUBYEAR = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        repository = RealBookRepository.getInstance();
        presenter = new BookPresenter(repository, this);
        repository.addObserver(presenter);
        repository.loadData();
        initializeRadioButton();
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


    public void initializeRadioButton() {
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.search_by_radiogroup);
        radioGroup.check(R.id.search_by_title_radiobutton);
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
                presenter.searchBy(s.toString());
            }
        });
    }

    public void onRadioButtonClicked(View view) {
        RadioButton radioButton = (RadioButton) findViewById(view.getId());
        switch (radioButton.getId()) {
            case R.id.search_by_title_radiobutton:
                presenter.onCheckRadioButton(SEARCH_BY_TITLE);
                break;

            case R.id.search_by_pubyear_radiobutton:
                presenter.onCheckRadioButton(SEARCH_BY_PUBYEAR);
                break;

            default:
                System.out.println("error");
        }
    }
}
