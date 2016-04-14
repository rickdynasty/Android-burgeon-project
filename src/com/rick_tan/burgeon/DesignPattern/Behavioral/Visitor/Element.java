 package com.rick_tan.burgeon.DesignPattern.Behavioral.Visitor;

/**
 * Created by yongchen on 2016/1/7.
 */
abstract class Element {
    public abstract void accept(IVisitor visitor);

    public abstract void doSomething();
}
