
package com.rickdynasty.tws.core.animation.ease;


public class CubicEaseOut extends BaseEase {

    public CubicEaseOut(float duration) {
        super(duration);
    }

    @Override
    public Float calculate(float t, float b, float c, float d) {
        return c * (t /= d) * t * t + b;
    }
}
