 package com.tencent.tws.burgeon.DesignPattern.Structural.Composite;

import android.util.Log;

/**
 * Created by Administrator on 2016/1/9.
 */
public class FinanceDepartment extends Company {
    public FinanceDepartment() {

    }

    public FinanceDepartment(String name) {
        super(name);
    }

    @Override
    protected void add(Company company) {

    }

    @Override
    protected void display(int depth) {
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < depth; i++) {
            sb.append("-");
        }

        Log.i("rick_Print:", new String(sb) + this.getName());
    }

    @Override
    protected void romove(Company company) {

    }
}
