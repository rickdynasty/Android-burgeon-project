 package com.rick_tan.burgeon.DesignPattern.Behavioral.State;

import android.util.Log;

/**
 * Created by Administrator on 2016/1/8.
 */
public class ForenoonState implements State {
    public void writeProgram(Work work) {
        // TODO Auto-generated method stub

        if (work != null) {
            //When working in the forenoon
            if (work.getHour() < 12) {
                Log.i("rick_Print:", "cur tiem:" + work.getHour() + " AM forenoon");
            } else {
                //Or go to work in the afternoon
                work.setCurrentState(new NoonState());
                work.writeProgram();
            }
        }
    }
}
