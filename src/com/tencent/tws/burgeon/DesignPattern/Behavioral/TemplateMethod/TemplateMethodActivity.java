 package com.tencent.tws.burgeon.DesignPattern.Behavioral.TemplateMethod;

import android.app.TwsActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.tencent.tws.devicemanager.R;

/**
 * Created by Administrator on 2016/1/7.
 */
public class TemplateMethodActivity extends TwsActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_designpattern_t);

        TextView tv = (TextView) findViewById(R.id.show_rlt_tv);
        tv.setText(R.string.designpattern_templatemethod_activity_des);
        tv.setMovementMethod(ScrollingMovementMethod.getInstance());

//        int[] a = {10, 32, 1, 9, 5, 7, 12, 0, 4, 3};
//        AbstractSort s = new ConcreteSort();
//        s.showSortResult(a);
    }
}
