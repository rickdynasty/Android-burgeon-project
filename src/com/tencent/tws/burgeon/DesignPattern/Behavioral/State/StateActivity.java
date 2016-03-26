 package com.tencent.tws.burgeon.DesignPattern.Behavioral.State;

import android.app.TwsActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.tencent.tws.devicemanager.R;

/**
 * Created by Administrator on 2016/1/7.
 */
public class StateActivity extends TwsActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_designpattern_t);

        TextView tv = (TextView) findViewById(R.id.show_rlt_tv);
        tv.setText(R.string.designpattern_State_Activity_des);
        tv.setMovementMethod(ScrollingMovementMethod.getInstance());

        realized();
    }

    // Test Code
    public void realized() {
        Work work = new Work();
        work.setHour(9);//Begin working
        work.writeProgram();//start to work
        work.setHour(11);
        work.writeProgram();
        work.setHour(12);
        work.writeProgram();
        work.setHour(13);
        work.writeProgram();
        work.setHour(14);
        work.writeProgram();
        work.setHour(17);

        //If there is no finish work to continue
        work.setFinished(true);

        work.writeProgram();
        work.setHour(21);
        work.writeProgram();
        work.setHour(22);
        work.setFinished(true);
        work.writeProgram();
    }
}
