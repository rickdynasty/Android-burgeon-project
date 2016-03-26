 package com.tencent.tws.burgeon.DesignPattern.Structural.Facade;

import android.util.Log;

/**
 * Created by Administrator on 2016/1/9.
 */
public class EatCenter {
    public void eat() {
        order();
        cooking();
        Log.i("rick_Print:", "eat");
    }

    private void cooking() {
        Log.i("rick_Print:", "cooking");
    }

    private void order() {
        Log.i("rick_Print:", "order");
    }
}
