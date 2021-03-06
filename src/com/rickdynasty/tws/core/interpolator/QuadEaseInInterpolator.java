
package com.rickdynasty.tws.core.interpolator;

public class QuadEaseInInterpolator extends BaseInterpolator {

    @Override
    public Float calculate(float t, float b, float c, float d) {
        return -c * (t /= d) * (t - 2) + b;
    }
}
