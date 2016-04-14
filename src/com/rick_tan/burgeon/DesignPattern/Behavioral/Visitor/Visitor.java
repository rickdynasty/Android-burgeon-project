 package com.rick_tan.burgeon.DesignPattern.Behavioral.Visitor;

/**
 * Created by yongchen on 2016/1/7.
 */
public class Visitor implements IVisitor {
    @Override
    public void visit(ConcreteElementX el1) {
        el1.doSomething();
    }
}
