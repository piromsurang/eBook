package com.example.piromsurang.ebookk.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.piromsurang.ebookk.BookPresenter;
import com.example.piromsurang.ebookk.R;
import com.example.piromsurang.ebookk.data.Book;
import com.example.piromsurang.ebookk.data.PromotionBook;
import com.example.piromsurang.ebookk.data.RealBookRepository;

import java.util.ArrayList;

/**
 * Created by Piromsurang on 4/29/2017 AD.
 */

public class PromotionsCustomAdapter extends BaseAdapter implements ListAdapter {

    private ArrayList<PromotionBook> list = new ArrayList<>();
    private Context context;
    private BookPresenter presenter;

    public PromotionsCustomAdapter(ArrayList<PromotionBook> list, BookPresenter presenter, Context context) {
        this.list = list;
        this.context = context;
        this.presenter = presenter;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Long.parseLong(list.get(position).getId());
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.promotion_listview_adapter, null);
        }

        //Handle TextView and display string from your list
        TextView listItemText = (TextView)view.findViewById(R.id.list_item_string_promotions);
        listItemText.setText(list.get(position).toString());


        //Handle buttons and add onClickListeners
        Button addBtn = (Button) view.findViewById(R.id.add_button);

        addBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                boolean check = false;
                for(Book a : list.get(position).getBookBundle()) {
                    if(presenter.getUser().getCart().getHistory().contains(a)) {
                        check = true;
                        presenter.createDialog(3);
                        break;
                    }
                }

                if(!check) {
                    presenter.getUser().addToCart(list.get(position));
                } else {

                }
                ArrayList<Book> temp = new ArrayList<>();
                for(PromotionBook b : list) {
                    temp.add(b);
                }
                presenter.displayList(temp);
            }
        });

        return view;
    }

}
