 package com.rick_tan.burgeon.DesignPattern.Structural.Proxy;

import android.util.Log;

/**
 * Created by Administrator on 2016/1/8.
 */
public class RealSubject implements Subject {
    @Override
    public void operate() {
        //realsubject operatestarted......
        Log.i("rick_Print:", "RealSubject operate");
    }
}
