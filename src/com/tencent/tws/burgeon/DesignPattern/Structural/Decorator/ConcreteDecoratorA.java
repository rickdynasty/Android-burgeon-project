 package com.tencent.tws.burgeon.DesignPattern.Structural.Decorator;

/**
 * Created by Administrator on 2016/1/9.
 */
public class ConcreteDecoratorA extends Decorator {
    public ConcreteDecoratorA(Component component) {
        super(component);
    }

    @Override
    public void operation() {
        super.operation();
        //code business logic
    }
}
