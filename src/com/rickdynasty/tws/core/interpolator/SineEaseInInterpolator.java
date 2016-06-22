
package com.rickdynasty.tws.core.interpolator;

public class SineEaseInInterpolator extends BaseInterpolator {

    @Override
    public Float calculate(float t, float b, float c, float d) {
        return c * (float) Math.sin(t / d * (Math.PI / 2)) + b;
    }
}
