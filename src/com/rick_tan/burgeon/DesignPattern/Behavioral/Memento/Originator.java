 package com.rick_tan.burgeon.DesignPattern.Behavioral.Memento;

/**
 * Created by yongchen on 2016/1/8.
 */
public class Originator {
    private String state = "";

    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public Memento createMemento(){
        return new Memento(this.state);
    }
    public void restoreMemento(Memento memento){
        this.setState(memento.getState());
    }
}
