 package com.rick_tan.burgeon.DesignPattern.Creational.Singleton;

/**
 * Created by Administrator on 2016/1/7.
 */
public class SluggardTypeSingleton {
    private static SluggardTypeSingleton singleton;

    private SluggardTypeSingleton() {
    }

    public static synchronized SluggardTypeSingleton getInstance() {
        if (singleton == null) {
            singleton = new SluggardTypeSingleton();
        }
        return singleton;
    }
}
