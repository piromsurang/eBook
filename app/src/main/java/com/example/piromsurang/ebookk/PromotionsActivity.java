package com.example.piromsurang.ebookk;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.piromsurang.ebookk.adapter.PromotionsCustomAdapter;
import com.example.piromsurang.ebookk.data.Book;
import com.example.piromsurang.ebookk.data.RealBookRepository;

import java.util.ArrayList;

public class PromotionsActivity extends AppCompatActivity implements BookView {

    private BookPresenter presenter;
    private RealBookRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promotions);

        repository = RealBookRepository.getInstance();
        presenter = new BookPresenter(repository, this);
        initializeListview();
    }

    private void initializeListview() {
        ListView listView = (ListView) findViewById(R.id.promotions_listview);
        PromotionsCustomAdapter adapter = new PromotionsCustomAdapter(repository.getPromotionBooks(), presenter, this);
        listView.setAdapter(adapter);
    }

    public void cancel(View view) {
        finish();
    }

    @Override
    public void displayList(ArrayList<Book> books) {
        initializeListview();
    }

    @Override
    public void createDialog(int b) {
        createAddingErrorDialog();
    }

    public void createAddingErrorDialog() {
        AlertDialog alertDialog = new AlertDialog.Builder(PromotionsActivity.this).create();
        alertDialog.setTitle("Adding to Cart Error");
        alertDialog.setMessage("There are some books that you already purchased in this bundle.");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "DISMISS",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

}
