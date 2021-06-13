package com.example.booking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.booking.db.Book;

import java.util.List;

public class userListAdapter extends BaseAdapter{
    private Context context;
    private List<Book> listbook;

    public userListAdapter(Context context, List<Book> listbook) {
        this.context = context;
        this.listbook = listbook;
    }

    @Override
    public int getCount() {
        return listbook.size();
    }

    @Override
    public Object getItem(int position) {
        return listbook.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listbook.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(R.layout.list_users,parent,false);

        Book book = listbook.get(position);

        TextView itemService = convertView.findViewById(R.id.textserv);
        TextView itemD = convertView.findViewById(R.id.textdate);
        TextView itemH = convertView.findViewById(R.id.texth);



        itemService.setText(book.getService());
        itemD.setText(book.getDate());
        itemH.setText(book.getTime());
        //dlt.setTag(book.getId());


        return convertView;
    }

}
