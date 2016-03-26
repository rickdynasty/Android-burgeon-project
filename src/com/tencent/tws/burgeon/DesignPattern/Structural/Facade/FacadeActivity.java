 package com.tencent.tws.burgeon.DesignPattern.Structural.Facade;

import android.app.TwsActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.tencent.tws.devicemanager.R;

/**
 * Created by Administrator on 2016/1/7.
 */
public class FacadeActivity extends TwsActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_designpattern_t);

        TextView tv = (TextView) findViewById(R.id.show_rlt_tv);
        tv.setText(R.string.designpattern_Facade_Activity_des);
        tv.setMovementMethod(ScrollingMovementMethod.getInstance());

        realized();
    }

    // Test Code
    public void realized() {
        ShoppingFacade shoppingFacade = new ShoppingFacade();
        shoppingFacade.clothing();
        shoppingFacade.eat();
    }
}
