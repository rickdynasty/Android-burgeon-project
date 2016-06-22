 package com.rick_tan.burgeon.DesignPattern.Behavioral.Visitor;

import android.app.TwsActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.rickdynasty.tws.burgeon.R;

/**
 * Created by Administrator on 2016/1/7.
 */
public class VisitorActivity extends TwsActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_designpattern_t);

        TextView tv = (TextView) findViewById(R.id.show_rlt_tv);
        tv.setText(R.string.designpattern_Visitor_Activity_des);
        tv.setMovementMethod(ScrollingMovementMethod.getInstance());

//        List<Element> list = ObjectStruture.getList();
//        for(Element e: list){
//            e.accept(new Visitor());
//        }
    }
}
