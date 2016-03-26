 package com.tencent.tws.burgeon.DesignPattern.Structural.Decorator;

/**
 * Created by Administrator on 2016/1/9.
 */
public class Decorator implements Component {
    private Component component;

    public Decorator(Component component){
        this.component = component;
    }

    @Override
    public void operation() {
        //Delegated to the component
        component.operation();
    }
}
