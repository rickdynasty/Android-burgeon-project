 package com.rick_tan.burgeon.DesignPattern.Behavioral.ChainOfResponsibility;

/**
 * Created by Administrator on 2016/1/7.
 */
public class Level {
    private int level = 0;

    public Level(int level) {
        this.level = level;
    }

    public boolean above(Level level) {
        if (this.level >= level.level) {
            return true;
        }
        return false;
    }
}
