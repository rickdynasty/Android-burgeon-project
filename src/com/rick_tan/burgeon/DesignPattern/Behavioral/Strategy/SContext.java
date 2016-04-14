 package com.rick_tan.burgeon.DesignPattern.Behavioral.Strategy;

/**
 * Created by yongchen on 2016/1/8.
 */
public class SContext {
    private IStrategy strategy;

    public SContext(IStrategy strategy){
        this.strategy = strategy;
    }

    public void execute(){
        strategy.doSomething();
    }
}
