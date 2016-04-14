 package com.rick_tan.burgeon.DesignPattern.Structural.Proxy;

/**
 * Created by Administrator on 2016/1/8.
 */
public class Proxy implements Subject {
    Subject subject;

    public Proxy(Subject subject) {
        this.subject = subject;
    }

    @Override
    public void operate() {
        //before operate......
        subject.operate();
        //after operate
    }
}
