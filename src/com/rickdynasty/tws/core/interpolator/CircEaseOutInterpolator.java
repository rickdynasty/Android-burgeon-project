
package com.rickdynasty.tws.core.interpolator;


public class CircEaseOutInterpolator extends BaseInterpolator {

    @Override
    public Float calculate(float t, float b, float c, float d) {
        return -c * ((float) Math.sqrt(1 - (t /= d) * t) - 1) + b;
    }
}
