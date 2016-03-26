 package com.tencent.tws.burgeon.DesignPattern.Structural.Flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/1/9.
 * Only available in CompositeFlyweight Pattern
 */
public class ConcreteCompositeFlyweight implements Flyweight {
    private Map<Character, Flyweight> files = new HashMap<Character, Flyweight>();

    /**
     * Add a New Simply flyweight objects into Map
     */
    public void add(Character key, Flyweight fly) {
        files.put(key, fly);
    }

    /**
     * External State As a parameter to method
     */
    @Override
    public void operation(String state) {
        Flyweight fly = null;
        for (Object o : files.keySet()) {
            fly = files.get(o);
            fly.operation(state);
        }

    }
}
