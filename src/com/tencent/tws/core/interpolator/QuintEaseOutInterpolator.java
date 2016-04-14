
package com.tencent.tws.core.interpolator;

public class QuintEaseOutInterpolator extends BaseInterpolator {

    @Override
    public Float calculate(float t, float b, float c, float d) {
        return c * (t /= d) * t * t * t * t + b;
    }
}
