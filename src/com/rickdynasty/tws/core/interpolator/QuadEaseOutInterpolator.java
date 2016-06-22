
package com.rickdynasty.tws.core.interpolator;

public class QuadEaseOutInterpolator extends BaseInterpolator {

    @Override
    public Float calculate(float t, float b, float c, float d) {
        return c * (t /= d) * t + b;
    }
}
