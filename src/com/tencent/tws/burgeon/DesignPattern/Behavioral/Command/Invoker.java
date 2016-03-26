 package com.tencent.tws.burgeon.DesignPattern.Behavioral.Command;

/**
 * Created by yongchen on 2016/1/7.
 */
public class Invoker {
    private Command command;
    public void setCommand(Command command) {
        this.command = command;
    }
    public void action(){
        this.command.execute();
    }
}
