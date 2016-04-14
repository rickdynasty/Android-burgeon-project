 package com.rick_tan.burgeon.DesignPattern.Structural.Flyweight;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/1/9.
 */
public class FlyweightFactory {
    private Map<Character, Flyweight> files = new HashMap<Character, Flyweight>();

    /**
     * CompositeFlyweight factory method
     */
    public Flyweight factory(List<Character> compositeState) {
        ConcreteCompositeFlyweight compositeFly = new ConcreteCompositeFlyweight();

        for (Character state : compositeState) {
            compositeFly.add(state, this.factory(state));
        }

        return compositeFly;
    }

    /**
     * Simply Flyweight factory method
     */
    public Flyweight factory(Character state) {
        //Firt, lookup object from the cache
        Flyweight fly = files.get(state);
        if (fly == null) {
            //if no exit,new Flyweight
            fly = new ConcreteFlyweight(state);
            //add Flyweight into cache
            files.put(state, fly);
        }
        return fly;
    }
}
