 package com.rick_tan.burgeon.DesignPattern.Behavioral.Memento;

import android.app.TwsActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

import com.rickdynasty.tws.burgeon.R;

/**
 * Created by Administrator on 2016/1/7.
 */
public class MementoActivity extends TwsActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_designpattern_t);

        TextView tv = (TextView) findViewById(R.id.show_rlt_tv);
        tv.setText(R.string.designpattern_Memento_Activity_des);
        tv.setMovementMethod(ScrollingMovementMethod.getInstance());

        realized();
    }

    // Test Code
    public void realized() {
        Originator originator = new Originator();
        originator.setState("State1");
        Log.i("rick_Print:", "init State:" + originator.getState());
        Caretaker caretaker = new Caretaker();
        caretaker.setMemento(originator.createMemento());
        originator.setState("State2");
        Log.i("rick_Print:", "changed State:" + originator.getState());
        originator.restoreMemento(caretaker.getMemento());
        Log.i("rick_Print:", "After the recovery State:" + originator.getState());
    }
}
