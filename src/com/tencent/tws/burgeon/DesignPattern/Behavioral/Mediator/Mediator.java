 package com.tencent.tws.burgeon.DesignPattern.Behavioral.Mediator;

/**
 * Created by yongchen on 2016/1/7.
 */
public class Mediator extends AbstractMediator {
    public Mediator(AbstractColleague a, AbstractColleague b) {
        super(a, b);
    }

    @Override
    public void AaffectB() {
        int number = A.getNumber();
        B.setNumber(number * 100);
    }

    @Override
    public void BaffectA() {
        int number = B.getNumber();
        A.setNumber(number / 100);
    }
}
