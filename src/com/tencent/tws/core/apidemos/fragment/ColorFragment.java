package com.tencent.tws.core.apidemos.fragment;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ColorFragment extends Fragment {

    LinearLayout linearLayout;
    String mTag;
    
    public ColorFragment(String tag)
    {
        this.mTag=tag;
    }
    
    
    
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        linearLayout = new LinearLayout(getActivity());
        linearLayout.setBackgroundColor(Color.RED);
        linearLayout.setGravity(Gravity.CENTER);
        TextView textView=new TextView(getActivity());
        textView.setTextColor(Color.WHITE);
        textView.setText(mTag);
        textView.setTextSize(50);
        linearLayout.addView(textView);
        return linearLayout;
    }
    
    public void setColor(int color)
    {
        linearLayout.setBackgroundColor(color);
    }
    
    
    
    

}
