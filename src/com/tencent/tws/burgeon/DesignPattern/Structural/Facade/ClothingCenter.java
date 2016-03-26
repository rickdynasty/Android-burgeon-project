 package com.tencent.tws.burgeon.DesignPattern.Structural.Facade;

import android.util.Log;

/**
 * Created by Administrator on 2016/1/9.
 */
public class ClothingCenter {
    public void sellClothing() {
        beforeClothing();
        Log.i("rick_Print:", "order and pay");
        afterClothing();
    }

    private void beforeClothing() {
        Log.i("rick_Print:", "try on clothes");
    }

    private void afterClothing() {
        Log.i("rick_Print:", "pack light clothes");
    }
}
