package com.example.piromsurang.ebookk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.piromsurang.ebookk.BookPresenter;
import com.example.piromsurang.ebookk.BookView;
import com.example.piromsurang.ebookk.R;

import java.util.ArrayList;

public class AddMoneyActivity extends AppCompatActivity {

    private final String AMOUNT_ADD_FUND = "adding_fund_amount_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_money);
    }

    public void sendMessage(View view) {
        EditText editText = (EditText) findViewById(R.id.add_money_edittext);
        double amount = Double.parseDouble(editText.getText().toString());
        Intent data = new Intent();
        data.putExtra(AMOUNT_ADD_FUND, amount);
        setResult(RESULT_OK, data);
        finish();
    }

    public void cancel(View view) {
        Intent data = new Intent();
        data.putExtra(AMOUNT_ADD_FUND, 0.0);
        setResult(RESULT_OK, data);
        finish();
    }
}
