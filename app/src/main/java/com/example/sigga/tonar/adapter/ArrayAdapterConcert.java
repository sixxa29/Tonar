package com.example.sigga.tonar.adapter;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.sigga.tonar.R;
import com.example.sigga.tonar.data.Results;

import java.util.ArrayList;

/**
 * Created by sigga on 19.10.2015.
 */
public class ArrayAdapterConcert extends ArrayAdapter<Results> {

    Context mContext;
    int layoutResourceId;
    ArrayList<Results> data = null;

    public ArrayAdapterConcert(Context mContext, int layoutResourceId, ArrayList<Results> data){
        super(mContext, layoutResourceId, data);

        this.mContext = mContext;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);
        }
        Results result = data.get(position);

        TextView textViewItem = (TextView) convertView.findViewById(R.id.textViewItem);
        textViewItem.setText(result.getEventDateName());
        textViewItem.setTag(result.getDateOfShow());
        return convertView;
    }
}


