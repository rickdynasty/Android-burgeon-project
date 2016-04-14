 package com.rick_tan.burgeon.DesignPattern.Creational.Prototype;

/**
 * Created by yongchen on 2016/1/7.
 */
public class Prototype implements Cloneable {
    public Prototype clone(){
        Prototype prototype = null;
        try{
            prototype = (Prototype)super.clone();
        }catch(CloneNotSupportedException e){
            e.printStackTrace();
        }
        return prototype;
    }
}
