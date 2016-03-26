 package com.tencent.tws.burgeon.DesignPattern.Behavioral.TemplateMethod;

/**
 * Created by yongchen on 2016/1/7.
 */
abstract class AbstractSort {
    protected abstract void sort(int[] array);

    public void showSortResult(int[] array){
        this.sort(array);
    }
}
