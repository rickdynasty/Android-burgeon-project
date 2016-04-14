 package com.rick_tan.burgeon.DesignPattern.Structural.Bridge;

/**
 * Created by Administrator on 2016/1/9.
 */
abstract class AbstractComputer {
    CpuAbility cpuAbility;

    public AbstractComputer(CpuAbility cpuAbility) {
        this.cpuAbility = cpuAbility;
    }

    public abstract void checkPcAbility();
}
