 package com.rick_tan.burgeon.DesignPattern.Creational.Builder;

/**
 * Created by Administrator on 2016/1/7.
 */
public interface ComputerBuilder {
    public ComputerBuilder buildCPU();

    public ComputerBuilder buildMotherboard();

    public ComputerBuilder buildCrate();

    public Computer getProduct();
}
