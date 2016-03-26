package com.tencent.tws.assistant.apidemos.fragment;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class MyListFragment extends ListFragment {
    
    String[] presidents = {"RED","BLUE","YELLOW","BLACK","WHITE"};
    onItemSelectedListener mListener;
    
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (onItemSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement onItemSelectedListener");
        }
    }
    
    
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(android.R.layout.list_content, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, presidents));
        }

    
    

    public interface onItemSelectedListener
    {
        public void onItemSelected(int position);
    }

    @Override
    public void onListItemClick(android.widget.ListView l, View v, int position, long id) {
        mListener.onItemSelected(position);
    }
    
}