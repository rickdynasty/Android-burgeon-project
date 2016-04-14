 package com.rick_tan.burgeon.DesignPattern.Behavioral.Command;

/**
 * Created by yongchen on 2016/1/7.
 */
public class ConcreteCommand extends Command {
    private Receiver receiver;
    public ConcreteCommand(Receiver receiver){
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        this.receiver.doSomething();
    }
}
