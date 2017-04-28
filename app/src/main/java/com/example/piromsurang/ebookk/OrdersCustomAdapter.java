package com.example.piromsurang.ebookk;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.piromsurang.ebookk.data.Book;
import com.example.piromsurang.ebookk.data.RealBookRepository;

import java.util.ArrayList;

/**
 * Created by Piromsurang on 4/28/2017 AD.
 */

public class OrdersCustomAdapter extends BaseAdapter implements ListAdapter {

    private ArrayList<Book> list = new ArrayList<>();
    private Context context;
    private BookPresenter presenter;

    public OrdersCustomAdapter(ArrayList<Book> list, BookPresenter presenter, Context context) {
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
            view = inflater.inflate(R.layout.orders_listview_adapter, null);
        }

        //Handle TextView and display string from your list
        TextView listItemText = (TextView)view.findViewById(R.id.list_item_string_orders);
        listItemText.setText(list.get(position).toString());
        ImageView imageView = (ImageView) view.findViewById(R.id.imageview_orders);
        imageView.setImageBitmap(RealBookRepository.getInstance().getBitmaps().get(position));


        //Handle buttons and add onClickListeners
        Button addBtn = (Button)view.findViewById(R.id.refund_button);

        addBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                presenter.createDialog(presenter.getUser().refundBook(list.get(position)));
                presenter.displayList(list);
            }
        });

        return view;
    }
}
