 package com.tencent.tws.burgeon.DesignPattern.Structural.Bridge;

import android.util.Log;

/**
 * Created by Administrator on 2016/1/9.
 */
public class IBMComputer extends AbstractComputer {
    public IBMComputer(CpuAbility cpuAbility) {
        super(cpuAbility);
    }

    @Override
    public void checkPcAbility() {
        Log.i("rick_Print:", "IBM " + super.cpuAbility.abilityCpu());
    }
}
