 package com.tencent.tws.burgeon.DesignPattern.Creational.Builder;

/**
 * Created by Administrator on 2016/1/7.
 */
public class HotDeskComputerBuilder implements ComputerBuilder {
    private Computer computer;

    public HotDeskComputerBuilder() {
        this.computer = new Computer();
    }

    @Override
    public ComputerBuilder buildCPU() {
        computer.cpu = new AMDCPU();
        return this;
    }

    @Override
    public ComputerBuilder buildMotherboard() {
        computer.motherboard = new ASUSMotherboard();
        return this;
    }

    @Override
    public ComputerBuilder buildCrate() {
        computer.crate = new CoolerMasterCrate();
        return this;
    }

    @Override
    public Computer getProduct() {
        buildMotherboard().buildCPU().buildCrate();
        return computer;
    }
}
