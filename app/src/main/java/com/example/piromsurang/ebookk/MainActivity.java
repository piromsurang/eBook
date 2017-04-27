package com.example.piromsurang.ebookk;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.view.ViewGroup.LayoutParams;

import com.example.piromsurang.ebookk.data.AddMoneyActivity;
import com.example.piromsurang.ebookk.data.Book;
import com.example.piromsurang.ebookk.data.MockUpBookRepository;
import com.example.piromsurang.ebookk.data.RealBookRepository;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BookView {

    private BookPresenter presenter;
    private RealBookRepository repository;
    private PopupWindow popupWindow;
    private LayoutInflater layoutInflater;

    private final int SEARCH_BY_TITLE = 1;
    private final int SEARCH_BY_PUBYEAR = 2;
    private final String CHECK_FUND = "Check Fund";
    private final String ADD_FUND = "Add Fund";
    private final String AMOUNT_ADD_FUND = "adding_fund_amount_key";
    private final int ADDING_FUND_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        repository = RealBookRepository.getInstance();
        presenter = new BookPresenter(repository, this);
        repository.addObserver(presenter);
        repository.loadData();
        initializeSpinner();
        initializeRadioButton();
        initializeEditText();
    }

    @Override
    public void displayList(ArrayList<Book> books) {
        ArrayAdapter<Book> adapter = new ArrayAdapter<Book>(this, android.R.layout.simple_list_item_1, books );
        ListView listView = (ListView) findViewById(R.id.show_list_listview);
        listView.setAdapter(adapter);
    }

    public void initializeSpinner() {
        Spinner spinner = (Spinner) findViewById(R.id.account_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.account_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                if(selectedItem.contains(CHECK_FUND)) {
                    initializePopupWindow(view);
                } else if(selectedItem.contains(ADD_FUND)) {
                    startAddingFund();
                }
                parent.setSelection(0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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

    public void initializePopupWindow(View view) {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Check Fund");
        String value = String.format("%.2f", presenter.getUser().getMoney());
        alertDialog.setMessage(value);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "DISMISS",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    public void startAddingFund() {
        Intent addFundIntent = new Intent(this, AddMoneyActivity.class );
        startActivityForResult(addFundIntent, ADDING_FUND_REQUEST);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == ADDING_FUND_REQUEST) {
            if (resultCode == RESULT_OK) {
                double amount = (double) data.getSerializableExtra(AMOUNT_ADD_FUND);
                presenter.addMoneyToUser(amount);
            }
        }
    }
}
