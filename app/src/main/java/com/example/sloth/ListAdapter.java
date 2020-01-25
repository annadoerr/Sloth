package com.example.sloth;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<ListItem> {

    public ListAdapter(Context context, ArrayList<ListItem> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListItem item = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_list_item, parent, false);
        }

        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView leafs = (TextView) convertView.findViewById(R.id.blaetter);
        TextView number = (TextView) convertView.findViewById(R.id.number);

        title.setText(item.title);
        leafs.setText(item.leafs);
        leafs.setText(item.number);

        return convertView;

    }




}
