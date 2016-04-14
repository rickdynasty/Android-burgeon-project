 package com.rick_tan.burgeon.DesignPattern.Structural.Bridge;

/**
 * Created by Administrator on 2016/1/9.
 */
public class IntelCpu implements CpuAbility {
    @Override
    public String abilityCpu() {
        return "Computer Performance is very good";
    }
}
