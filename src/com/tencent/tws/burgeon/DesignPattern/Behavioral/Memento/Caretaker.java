 package com.tencent.tws.burgeon.DesignPattern.Behavioral.Memento;

/**
 * Created by yongchen on 2016/1/8.
 */
public class Caretaker {
    private Memento memento;
    public Memento getMemento(){
        return memento;
    }
    public void setMemento(Memento memento){
        this.memento = memento;
    }
}
