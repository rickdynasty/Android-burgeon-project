 package com.rick_tan.burgeon.DesignPattern.Behavioral.Observer;

import java.util.Vector;

/**
 * Created by yongchen on 2016/1/7.
 */
abstract class Observable {
    private Vector<Observer> obs = new Vector<Observer>();

    public void addObserver(Observer obs) {
        this.obs.add(obs);
    }

    public void delObserver(Observer obs) {
        this.obs.remove(obs);
    }

    protected void notifyObserver() {
        for (Observer o : obs) {
            o.update();
        }
    }

    public abstract void doSomething();
}
