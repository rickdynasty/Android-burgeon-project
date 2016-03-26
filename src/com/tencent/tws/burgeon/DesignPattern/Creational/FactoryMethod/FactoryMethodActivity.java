 package com.tencent.tws.burgeon.DesignPattern.Creational.FactoryMethod;

import android.app.TwsActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tencent.tws.devicemanager.R;

/**
 * Created by Administrator on 2016/1/7.
 */
public class FactoryMethodActivity extends TwsActivity implements View.OnClickListener {
    private TextView tv;
    private Factory factory;
    private IProduct iProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_designpattern_t);

        Button btn_action = (Button) findViewById(R.id.btn_action);
        btn_action.setVisibility(View.VISIBLE);
        btn_action.setOnClickListener(this);
        btn_action.setText(R.string.button_factory_action);

        tv = (TextView) findViewById(R.id.show_rlt_tv);
        tv.setHint(R.string.designpattern_factorymethod_activity_des);
        tv.setMovementMethod(ScrollingMovementMethod.getInstance());
        factory = new Factory();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_action:
                iProduct = factory.createProduct(ProductA.class);
                tv.setText(iProduct.productMethod());
                break;
            default:
                break;
        }
    }
}
