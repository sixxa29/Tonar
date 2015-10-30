package com.example.sigga.tonar.fragment;


import android.os.Bundle;
import android.widget.ListView;


public class ConcertListFragment extends MasterFragment{

    public final static String ARG_POSITION = "position";
    int mCurrentPosition = -1;
   // private ArrayAdapterConcert concertAdapter = new ArrayAdapterConcert(getActivity(), R.layout.list_view_row_item, data);

    /*public void dataArr(ArrayList<Results> data){
        this.data = data;
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

      //  setListAdapter(concertAdapter);
    }


    @Override
    public void onStart() {
        super.onStart();
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }

    /*   public void updateConcertView( int position, View view, ViewGroup parent ) {
        arrayAdapterConcert.getView(position, view, parent);
    }*/

}
