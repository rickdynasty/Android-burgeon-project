 package com.rick_tan.burgeon.DesignPattern.Behavioral.Command;

import android.app.TwsActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.tencent.tws.burgeon.R;

/**
 * Created by Administrator on 2016/1/7.
 */
public class CommandActivity extends TwsActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_designpattern_t);

        TextView tv = (TextView) findViewById(R.id.show_rlt_tv);
        tv.setText(R.string.designpattern_Command_Activity_des);
        tv.setMovementMethod(ScrollingMovementMethod.getInstance());

        realized();
    }

    // Test Code
    public void realized() {
        Receiver receiver = new Receiver();
        Command command = new ConcreteCommand(receiver);
        //The client direct way follow specific orders
        command.execute();

        //The client by the caller to execute the command
        Invoker invoker = new Invoker();
        invoker.setCommand(command);
        invoker.action();
    }
}
