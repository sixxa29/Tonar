package com.example.sigga.tonar.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.sigga.tonar.R;
import com.example.sigga.tonar.data.Results;
import com.example.sigga.tonar.formats.DateFormats;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;


public class ArrayAdapterConcert extends ArrayAdapter<Results>  {

    Context mContext;
    int layoutResourceId;
    ArrayList<Results> data = null;
    boolean isHeader = true;
    DateFormats forms = new DateFormats();

    public ArrayAdapterConcert(Context mContext, int layoutResourceId, ArrayList<Results> data){
        super(mContext, layoutResourceId, data);

        this.mContext = mContext;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
        this.isHeader = true;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Results result = data.get(position);
        LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();

        if(convertView == null){

            convertView = inflater.inflate(layoutResourceId, parent, false);
            isHeader = false;


        }
            TextView textViewItem = (TextView) convertView.findViewById(R.id.textViewItem);
            TextView textViewItem2 = (TextView) convertView.findViewById(R.id.textViewItem2);
            TextView textViewItem3 = (TextView) convertView.findViewById(R.id.textViewItem3);

            textViewItem.setText(result.getEventDateName());
            textViewItem.setTag(result.getDateOfShow());

            String dateInString = result.getDateOfShow();

            textViewItem2.setText(forms.getDateFormat(dateInString));
            textViewItem3.setText("Kl " + forms.getTimeFormat(dateInString));

            ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);
            Picasso
                    .with(mContext)
                    .load(result.getImageSource())
                    .fit()
                    .into(imageView);
        return convertView;
    }

}


