package com.example.piromsurang.ebookk;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.piromsurang.ebookk.data.Book;

import java.util.ArrayList;

/**
 * Created by Piromsurang on 4/27/2017 AD.
 */

public class CustomAdapter extends BaseAdapter implements ListAdapter {

    private ArrayList<Book> list = new ArrayList<>();
    private Context context;
    private BookPresenter presenter;

    public CustomAdapter(ArrayList<Book> list, BookPresenter presenter, Context context) {
        this.list = list;
        this.presenter = presenter;
        this.context = context;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.main_listview_adapter, null);
        }

        //Handle TextView and display string from your list
        TextView listItemText = (TextView)view.findViewById(R.id.list_item_string);
        listItemText.setText(list.get(position).toString());

        //Handle buttons and add onClickListeners
        Button addBtn = (Button)view.findViewById(R.id.add_btn);

        addBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                presenter.getUser().addToCart(list.get(position));
            }
        });

        return view;
    }
}
