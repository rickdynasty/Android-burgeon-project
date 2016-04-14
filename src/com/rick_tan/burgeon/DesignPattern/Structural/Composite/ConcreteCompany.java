 package com.rick_tan.burgeon.DesignPattern.Structural.Composite;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

/**
 * Created by Administrator on 2016/1/9.
 */
public class ConcreteCompany extends Company {
    private List<Company> cList;

    public ConcreteCompany() {
        cList = new ArrayList<Company>();
    }

    public ConcreteCompany(String name) {
        super(name);
        cList = new ArrayList<Company>();
    }

    @Override
    protected void add(Company company) {
        cList.add(company);
    }

    @Override
    protected void display(int depth) {
        // TODO Auto-generated method stub
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < depth; i++) {
            sb.append("-");
        }
        Log.i("rick_Print:", new String(sb) + this.getName());
        for (Company c : cList) {
            c.display(depth + 2);
        }
    }

    @Override
    protected void romove(Company company) {
        cList.remove(company);
    }
}
