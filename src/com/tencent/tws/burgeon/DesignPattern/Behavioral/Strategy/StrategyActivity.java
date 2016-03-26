 package com.tencent.tws.burgeon.DesignPattern.Behavioral.Strategy;

import android.app.TwsActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.tencent.tws.devicemanager.R;

/**
 * Created by Administrator on 2016/1/7.
 */
public class StrategyActivity extends TwsActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_designpattern_t);

        TextView tv = (TextView) findViewById(R.id.show_rlt_tv);
        tv.setText(R.string.designpattern_Strategy_Activity_des);
        tv.setMovementMethod(ScrollingMovementMethod.getInstance());

        realized();
    }

    // Test Code
    public void realized() {
        SContext context;
        //executive strategy 1
        context = new SContext(new ConcreteStrategy1());
        context.execute();

        //executive strategy 2
        context = new SContext(new ConcreteStrategy2());
        context.execute();
    }
}
