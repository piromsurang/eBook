package com.example.piromsurang.ebookk;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.piromsurang.ebookk.data.Book;
import com.example.piromsurang.ebookk.data.RealBookRepository;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity implements BookView {

    private BookPresenter presenter;
    private RealBookRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        repository = RealBookRepository.getInstance();
        presenter = new BookPresenter(repository, this);
        initializeListview();
        initializeShowMoney();
    }

    public void initializeShowMoney() {
        TextView textView = (TextView) findViewById(R.id.show_amount_user_textview);
        String text = String.format("%.2f", presenter.getUser().getMoney());
        textView.setText(text);
    }

    public void initializeListview() {
        ListView listView = (ListView) findViewById(R.id.cart_listview);
        CartCustomAdapter adapter = new CartCustomAdapter(presenter.getUser().getCart().getSelectedBooks(), presenter, this);
        listView.setAdapter(adapter);
        showTotal();
    }

    public void proceedCheckout(View view) {

        if(presenter.getUser().getMoney() < presenter.getTotal() ) {
            AlertDialog alertDialog = new AlertDialog.Builder(CartActivity.this).create();
            alertDialog.setTitle("Checkout Error");
            alertDialog.setMessage("Sorry, you don't have enough money to checkout.");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "DISMISS",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        } else {
            AlertDialog alertDialog = new AlertDialog.Builder(CartActivity.this).create();
            alertDialog.setTitle("Checkout Completed");
            alertDialog.setMessage("Thank you for purchased books with us.");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "DISMISS",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            presenter.getUser().pay(presenter.getTotal());
                            presenter.getUser().getCart().clearCart();
                            initializeShowMoney();
                            presenter.displayList(presenter.getUser().getCart().getSelectedBooks());
                        }
                    });
            alertDialog.show();
        }

    }

    public void cancel(View view) {
        finish();
    }

    @Override
    public void displayList(ArrayList<Book> books) {
        initializeListview();
    }

    public void showTotal() {
        TextView textView = (TextView) findViewById(R.id.show_total_amount);
        textView.setText(presenter.getTotal() + "");
    }
}
