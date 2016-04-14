 package com.rick_tan.burgeon.DesignPattern.Structural.Decorator;

import android.app.TwsActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.tencent.tws.burgeon.R;

/**
 * Created by Administrator on 2016/1/7.
 */
public class DecoratorActivity extends TwsActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_designpattern_t);

        TextView tv = (TextView) findViewById(R.id.show_rlt_tv);
        tv.setText(R.string.designpattern_Decorator_Activity_des);
        tv.setMovementMethod(ScrollingMovementMethod.getInstance());

        realized();
    }

    // Test Code
    public void realized() {
    }
}
