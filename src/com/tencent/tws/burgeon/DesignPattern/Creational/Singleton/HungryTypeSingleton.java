 package com.tencent.tws.burgeon.DesignPattern.Creational.Singleton;

/**
 * Created by Administrator on 2016/1/7.
 */
public class HungryTypeSingleton {
    private static HungryTypeSingleton singleton = new HungryTypeSingleton();

    private HungryTypeSingleton() {
    }

    public static HungryTypeSingleton getInstance() {
        return singleton;
    }
}
