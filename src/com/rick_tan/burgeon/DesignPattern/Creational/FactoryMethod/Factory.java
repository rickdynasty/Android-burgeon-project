 package com.rick_tan.burgeon.DesignPattern.Creational.FactoryMethod;

/**
 * Created by Administrator on 2016/1/7.
 */
public class Factory implements IFactory {
    @Override
    public <T extends IProduct> T createProduct(Class<T> clz) {
        IProduct product = null;
        try {
            product = (IProduct) Class.forName(clz.getName()).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) product;
    }
}
