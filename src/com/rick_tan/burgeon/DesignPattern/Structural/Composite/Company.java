 package com.rick_tan.burgeon.DesignPattern.Structural.Composite;

/**
 * Created by Administrator on 2016/1/9.
 */
public abstract class Company {
    private String name;

    public Company(String name) {
        this.name = name;
    }

    public Company() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected abstract void add(Company company);

    protected abstract void romove(Company company);

    protected abstract void display(int depth);
}
