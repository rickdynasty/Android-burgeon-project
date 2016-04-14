 package com.rick_tan.burgeon.DesignPattern.Creational.AbstractFactory;

/**
 * Created by Administrator on 2016/1/7.
 */
public interface ICarFactory {
    public ITire createTire();

    public IEngine createEngine();

    public IBrake createBrake();
}
