 package com.rick_tan.burgeon.DesignPattern.Behavioral.ChainOfResponsibility;

/**
 * Created by Administrator on 2016/1/7.
 */
public class ConcreteHandler1 extends Handler {
    protected Level getHandlerLevel() {
        return new Level(1);
    }

    public Response response(Request request) {
        //Request by the processor 1 for processing
        return null;
    }
}
