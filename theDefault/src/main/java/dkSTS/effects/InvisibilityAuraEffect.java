package dkSTS.effects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;

public class InvisibilityAuraEffect extends AbstractStanceAuraEffect {


    public InvisibilityAuraEffect() {
        super(generateColor());
    }

    public static Color generateColor() {
        float r = MathUtils.random(0.3F, 0.7F);
        return new Color(r,r,r,0.0F);
    }
}
