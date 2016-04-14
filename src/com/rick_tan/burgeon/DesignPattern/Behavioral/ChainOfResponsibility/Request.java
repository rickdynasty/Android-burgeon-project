 package com.rick_tan.burgeon.DesignPattern.Behavioral.ChainOfResponsibility;

/**
 * Created by Administrator on 2016/1/7.
 */
public class Request {
    Level level;

    public Request(Level level) {
        this.level = level;
    }

    public Level getLevel() {
        return level;
    }
}
