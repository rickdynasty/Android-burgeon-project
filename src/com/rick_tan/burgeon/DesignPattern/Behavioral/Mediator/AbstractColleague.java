 package com.rick_tan.burgeon.DesignPattern.Behavioral.Mediator;

/**
 * Created by yongchen on 2016/1/7.
 */
abstract class AbstractColleague {
    protected int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    //Abstract methods, modify the Numbers at the same time modify associated objects
    public abstract void setNumber(int number, AbstractMediator coll);
}
