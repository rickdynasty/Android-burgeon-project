 package com.tencent.tws.burgeon.DesignPattern.Behavioral.Iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yongchen on 2016/1/8.
 */
public class ConcreteAggregate implements  Aggregate {
    private List list = new ArrayList();
    public void add(Object obj) {
        list.add(obj);
    }

    public Iterator iterator() {
        return new ConcreteIterator(list);
    }

    public void remove(Object obj) {
        list.remove(obj);
    }
}
