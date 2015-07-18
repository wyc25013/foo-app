package com.example.wangyicheng.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomRow extends ArrayAdapter<String> {

    public CustomRow(Context context, String[] denNames) {
        super(context, R.layout.custom_row, denNames);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater li = LayoutInflater.from(getContext());
        View customView = li.inflate(R.layout.custom_row, parent, false);

        String denNameItem = getItem(position);
        TextView denName = (TextView) customView.findViewById(R.id.denName);
        ImageView denImg = (ImageView) customView.findViewById(R.id.denIMG);

        denName.setText(denNameItem);
        denImg.setImageResource(R.drawable.bk);
        return customView;
    }
}
