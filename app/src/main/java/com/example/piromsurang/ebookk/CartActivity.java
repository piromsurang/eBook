package com.example.piromsurang.ebookk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import static com.example.piromsurang.ebookk.MainActivity.SERIAL_USER;

public class CartActivity extends AppCompatActivity {

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        getMyIntent();
        initializeListview();
    }

    public void getMyIntent() {
        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra(SERIAL_USER);

    }

    public void initializeListview() {
        ListView listView = (ListView) findViewById(R.id.cart_listview);
        CartCustomAdapter adapter = new CartCustomAdapter(user.getCart().getSelectedBooks(), user, this);
        listView.setAdapter(adapter);
    }

    public void proceedCheckout(View view) {

    }

    public void cancel(View view) {
        finish();
    }
}
