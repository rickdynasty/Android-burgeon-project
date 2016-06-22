 package com.rick_tan.burgeon.DesignPattern.Structural.Proxy;

import android.app.TwsActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.rickdynasty.tws.burgeon.R;

/**
 * Created by Administrator on 2016/1/7.
 */
public class ProxyActivity extends TwsActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_designpattern_t);

        TextView tv = (TextView) findViewById(R.id.show_rlt_tv);
        tv.setText(R.string.designpattern_Proxy_Activity_des);
        tv.setMovementMethod(ScrollingMovementMethod.getInstance());

        realized();
    }

    // Test Code
    public void realized() {
        Subject subject = new RealSubject();
        Proxy proxy = new Proxy(subject);
        proxy.operate();
    }
}
