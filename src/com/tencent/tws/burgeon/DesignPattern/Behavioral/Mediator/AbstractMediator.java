 package com.tencent.tws.burgeon.DesignPattern.Behavioral.Mediator;

/**
 * Created by yongchen on 2016/1/7.
 */
abstract class AbstractMediator {
    protected AbstractColleague A;
    protected AbstractColleague B;

    public AbstractMediator(AbstractColleague a, AbstractColleague b) {
        A = a;
        B = b;
    }

    public abstract void AaffectB();

    public abstract void BaffectA();
}
