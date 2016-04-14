 package com.rick_tan.burgeon.DesignPattern.Structural.Facade;

/**
 * Created by Administrator on 2016/1/9.
 */
public class ShoppingFacade {
    public void clothing(){
        ClothingCenter clothing = new ClothingCenter();
        clothing.sellClothing();
    }

    public void eat(){
        EatCenter eat = new EatCenter();
        eat.eat();
    }
}
