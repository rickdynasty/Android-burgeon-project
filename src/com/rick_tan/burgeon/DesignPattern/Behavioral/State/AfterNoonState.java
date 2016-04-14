 package com.rick_tan.burgeon.DesignPattern.Behavioral.State;

import android.util.Log;

/**
 * Created by Administrator on 2016/1/8.
 */
public class AfterNoonState implements State {
    public void writeProgram(Work work) {
        // TODO Auto-generated method stub

        if(work != null){
            //When the afternoon
            if(work.getHour() <17){
                Log.i("rick_Print:", "cur time:" + work.getHour() + " PM");
            }else{
                //Or go to the Evening work
                work.setCurrentState(new EveningState());
                work.writeProgram();
            }
        }
    }
}
