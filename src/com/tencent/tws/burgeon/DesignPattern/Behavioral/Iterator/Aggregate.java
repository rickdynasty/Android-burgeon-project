 package com.tencent.tws.burgeon.DesignPattern.Behavioral.Iterator;

/**
 * Created by yongchen on 2016/1/8.
 */
interface Aggregate {
    public void add(Object obj);
    public void remove(Object obj);
    public Iterator iterator();
}
