 package com.tencent.tws.burgeon.DesignPattern.Behavioral.Visitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by yongchen on 2016/1/7.
 */
public class ObjectStruture {
    public static List<Element> getList() {
        List<Element> list = new ArrayList<Element>();
        Random ran = new Random();
        for (int i = 0; i < 10; i++) {
            int a = ran.nextInt(100);
            if (a > 50) {
                list.add(new ConcreteElementX());
            } else {
                //list.add(new ConcreteElementX2());
            }
        }
        return list;
    }
}
