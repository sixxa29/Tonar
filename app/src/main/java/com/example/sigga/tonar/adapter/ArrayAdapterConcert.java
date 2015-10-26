package com.example.sigga.tonar.adapter;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sigga.tonar.R;
import com.example.sigga.tonar.data.Results;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Target;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by sigga on 19.10.2015.
 */
public class ArrayAdapterConcert extends ArrayAdapter<Results>  {

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
        TextView textViewItem2 = (TextView) convertView.findViewById(R.id.textViewItem2);
        textViewItem.setText(result.getEventDateName());
        textViewItem.setTag(result.getDateOfShow());

        String dateInString = result.getDateOfShow();
        SimpleDateFormat oldformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        String bla = dateInString.split("T")[0];
        try {
            SimpleDateFormat newformat = new SimpleDateFormat("dd-MM-yyyy");
            Date date = oldformat.parse(dateInString);
            DateFormat formats = null;

            String reformattedStr = newformat.format(date);
            String rerere = formats.getDateInstance().format(date);
            textViewItem2.setText(rerere);
        }
        catch (Exception e){
            return null;
        }



        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);

        Picasso
                .with(mContext)
                .load(result.getImageSource())
                .fit()
                .into(imageView);
        return convertView;
    }




}


