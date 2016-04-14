package com.tencent.tws.core.apidemos.fragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.TwsActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.tencent.tws.core.apidemos.fragment.MyListFragment.onItemSelectedListener;
import com.tencent.tws.burgeon.R;

public class FragmentDemo extends TwsActivity implements onItemSelectedListener,OnClickListener {
    
    Button btnAdd,btnRemove,btnReplace;
    FragmentTransaction fragmentTransaction;
    ColorFragment colorFragment1,colorFragment2;
    Fragment listFragment;
    int colortype=1;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fgm_demo);
        colorFragment1=new ColorFragment("F 1");
        colorFragment2=new ColorFragment("F 2");
        listFragment=new MyListFragment();
        btnAdd=(Button) findViewById(R.id.btnAdd);
        btnRemove=(Button) findViewById(R.id.btnRemove);
        btnReplace=(Button) findViewById(R.id.btnReplace);
        btnAdd.setOnClickListener(this);
        btnRemove.setOnClickListener(this);
        btnReplace.setOnClickListener(this);
        
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
        case R.id.btnAdd:
            fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.containerUp, listFragment);
            
            if(colortype==1)
                fragmentTransaction.add(R.id.containerDown, colorFragment1);
            else
                fragmentTransaction.add(R.id.containerDown, colorFragment2);
            fragmentTransaction.commit();
            
            btnAdd.setClickable(false);
            btnRemove.setClickable(true);
            btnReplace.setClickable(true);
            break;
        case R.id.btnRemove:
            fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.remove(listFragment);
            if(colortype==1)
                fragmentTransaction.remove(colorFragment1);
            else
                fragmentTransaction.remove(colorFragment2);
            fragmentTransaction.commit();
            
            btnAdd.setClickable(true);
            btnRemove.setClickable(false);
            btnReplace.setClickable(false);
            break;
        case R.id.btnReplace:
            fragmentTransaction = getFragmentManager().beginTransaction();
            if(colortype==1)
            {
                fragmentTransaction.replace(R.id.containerDown, colorFragment2);
                colortype=2;
            }
            else
            {
                fragmentTransaction.replace(R.id.containerDown, colorFragment1);
                colortype=1;
            }
            fragmentTransaction.commit();
            
            btnAdd.setClickable(false);
            btnRemove.setClickable(true);
            btnReplace.setClickable(true);
            break;
        }
        
    }
    
    
    
    
    @Override
    public void onItemSelected(int position) {
        // TODO Auto-generated method stub
        

        int color=Color.RED;
        if(position==0)
            color=Color.RED;
        else if(position==1)
            color=Color.BLUE;
        else if(position==2)
            color=Color.YELLOW;
        else if(position==3)
            color=Color.BLACK;
        else if(position==4)
            color=Color.WHITE;
        if(colortype==1)
            colorFragment1.setColor(color);
        else
            colorFragment2.setColor(color);
    }






    
}
