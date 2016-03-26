 package com.tencent.tws.burgeon.DesignPattern.Behavioral.Observer;

/**
 * Created by yongchen on 2016/1/7.
 */
public class ConcreteObservable extends Observable {
    @Override
    public void doSomething() {
        notifyObserver();
    }
}
