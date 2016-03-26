 package com.tencent.tws.burgeon.DesignPattern.Structural.Flyweight;

import android.util.Log;

/**
 * Created by Administrator on 2016/1/9.
 */
public class ConcreteFlyweight implements Flyweight {
    private Character intrinsicState = null;

    /**
     * constructor, Intrinsic state passed as a parameter
     */
    public ConcreteFlyweight(Character state) {
        this.intrinsicState = state;
    }


    /**
     * External State As a parameter to method,Changing the behavior of the method, no change Intrinsic state
     */
    @Override
    public void operation(String state) {
        Log.i("rick_Print:","Intrinsic State = " + this.intrinsicState);
        Log.i("rick_Print:", "Extrinsic State = " + state);
    }
}
