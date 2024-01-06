package dkSTS.effects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;

public class BloodlustAuraEffect extends AbstractStanceAuraEffect {

    public BloodlustAuraEffect() {
        super(new Color(MathUtils.random(0.7F, 0.8F), MathUtils.random(0.1F, 0.2F), MathUtils.random(0.1F, 0.2F), 0.0F));
    }
}
