 package com.rick_tan.burgeon.DesignPattern.Behavioral.State;

import android.util.Log;

/**
 * Created by Administrator on 2016/1/8.
 */
public class NoonState implements State {
    public void writeProgram(Work work) {
        // TODO Auto-generated method stub

        if(work != null){
            //When the noon
            if(work.getHour() <13){
                Log.i("rick_Print:", "cur time:" + work.getHour() + " ,A little sleepy");
            }else{
                //Or go to work in the afternoon
                work.setCurrentState(new AfterNoonState());
                work.writeProgram();
            }
        }
    }
}
