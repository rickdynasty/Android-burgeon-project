 package com.rick_tan.burgeon.DesignPattern.Behavioral.Iterator;

import android.app.TwsActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

import com.rickdynasty.tws.burgeon.R;

/**
 * Created by Administrator on 2016/1/7.
 */
public class IteratorActivity extends TwsActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_designpattern_t);

        TextView tv = (TextView) findViewById(R.id.show_rlt_tv);
        tv.setText(R.string.designpattern_Iterator_Activity_des);
        tv.setMovementMethod(ScrollingMovementMethod.getInstance());

        realized();
    }

    // Test Code
    public void realized() {
        Aggregate ag = new ConcreteAggregate();
        ag.add("xiaoMing");
        ag.add("xiaoHong");
        ag.add("xiaoGang");
        Iterator it = ag.iterator();
        while(it.hasNext()){
            String str = (String)it.next();
            Log.i("rick_Print:", str);
        }
    }
}
