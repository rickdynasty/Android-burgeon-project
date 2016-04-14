 package com.rick_tan.burgeon.DesignPattern.Behavioral.State;

import android.util.Log;

/**
 * Created by Administrator on 2016/1/8.
 */
public class EveningState implements State {
    public void writeProgram(Work work) {
        if(work != null){
            //When the afternoon
            if(work.taskFinished()){
                Log.i("rick_Print:", "cur time:" + work.getHour() + " Go off work...");
                work.setCurrentState(new SleepingState());
                work.writeProgram();
            }else if(work.getHour() <21){
                Log.i("rick_Print:", "cur time:" + work.getHour() + " Still working...");
            }
        }
    }
}
