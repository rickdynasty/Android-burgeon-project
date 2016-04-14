 package com.rick_tan.burgeon.DesignPattern.Behavioral.Memento;

/**
 * Created by yongchen on 2016/1/8.
 */
public class Memento {
    private String state = "";
    public Memento(String state){
        this.state = state;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
}
