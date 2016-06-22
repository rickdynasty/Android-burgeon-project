 package com.rick_tan.burgeon.DesignPattern.Behavioral.Mediator;

import android.app.TwsActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.rickdynasty.tws.burgeon.R;

/**
 * Created by Administrator on 2016/1/7.
 */
public class MediatorActivity extends TwsActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_designpattern_t);

        TextView tv = (TextView) findViewById(R.id.show_rlt_tv);
        tv.setText(R.string.designpattern_Mediator_activity_des);
        tv.setMovementMethod(ScrollingMovementMethod.getInstance());
    }
}
