
package com.rickdynasty.tws.core.interpolator;

public class CubicEaseInInterpolator extends BaseInterpolator {

    @Override
    public Float calculate(float t, float b, float c, float d) {
        return c * ((t = t / d - 1) * t * t + 1) + b;
    }
}
