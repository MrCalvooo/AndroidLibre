package com.videoyt.a07spinnerimagenes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class Adaptador extends BaseAdapter {

    private Context context;
    private String[] paises;
    private int[] banderas;

    public Adaptador(Context context, String[] paises, int[] banderas) {
        this.context = context;
        this.paises = paises;
        this.banderas = banderas;
    }

    @Override
    public int getCount() {
        return paises.length;
    }

    @Override
    public Object getItem(int position) {
        return paises[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(R.layout.itemspinner, null);
        ImageView imageView = convertView.findViewById(R.id.imgvBandera);
        TextView textView = convertView.findViewById(R.id.tvPais);
        imageView.setImageResource(banderas[position]);
        textView.setText(paises[position]);
        return convertView;
    }
}
