package com.example.piromsurang.ebookk;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.piromsurang.ebookk.data.Book;
import com.example.piromsurang.ebookk.data.RealBookRepository;

import java.util.ArrayList;

public class OrdersActivity extends AppCompatActivity implements BookView {

    private BookPresenter presenter;
    private RealBookRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        repository = RealBookRepository.getInstance();
        presenter = new BookPresenter(repository, this);

        initializeShowMoney();
        initializeListView();
    }

    public void initializeShowMoney() {
        TextView textView = (TextView) findViewById(R.id.show_amount_user_orders_textview);
        String text = String.format("%.2f", presenter.getUser().getMoney());
        textView.setText(text);
    }

    public void back(View view) {
        finish();
    }

    public void initializeListView() {
        ListView listView = (ListView) findViewById(R.id.show_orders_listview);
        System.out.println(presenter.getUser().getCart().getHistory().toString());
        OrdersCustomAdapter ordersCustomAdapter = new OrdersCustomAdapter(presenter.getUser().getCart().getHistory(), presenter, this);
        listView.setAdapter(ordersCustomAdapter);
    }

    @Override
    public void displayList(ArrayList<Book> books) {
        initializeListView();
        initializeShowMoney();
    }

    @Override
    public void createDialog(boolean b) {
        if(b) {
            createRefundableDialog();
        } else {
            createUnrefundableDialog();
        }
    }

    public void createRefundableDialog() {
        AlertDialog alertDialog = new AlertDialog.Builder(OrdersActivity.this).create();
        alertDialog.setTitle("Refund Book");
        alertDialog.setMessage("This book is refunded.");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "DISMISS",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    public void createUnrefundableDialog() {
        AlertDialog alertDialog = new AlertDialog.Builder(OrdersActivity.this).create();
        alertDialog.setTitle("Refund Book");
        alertDialog.setMessage("Sorry, you can't refund this book due to the purchase is over 5 minutes.");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "DISMISS",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
}
