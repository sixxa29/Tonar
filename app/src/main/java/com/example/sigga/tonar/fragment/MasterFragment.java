package com.example.sigga.tonar.fragment;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by sigga on 27.10.2015.
 */
public class MasterFragment extends ListFragment {
/*
    public interface OnButtonSelectedListeners {
        public void onButtonSelected( int position );
    }
    OnButtonSelectedListeners mListeners;
*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] Headlines = {
                "Allir tónleikar",
                "Inga er best ef þetta virkar"
        };
        // Create an array adapter for the list view, using the Ipsum headlines array
        setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_expandable_list_item_1, Headlines ));
    }
/*
    @Override
    public void onAttach( Activity activity ) {
        super.onAttach( activity );

        mListeners = (OnButtonSelectedListeners) activity;
        // ...
    }

    @Override
    public void onListItemClick( ListView l, View v, int position, long id ) {

        mListeners.onButtonSelected( position );
        getListView().setItemChecked( position, true );
    }

    @Override
    public void onStart() {
        super.onStart();
        getListView().setChoiceMode( ListView.CHOICE_MODE_SINGLE );
    }
    */
}
