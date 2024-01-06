package dkSTS.effects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.stances.DivinityStance;
import com.megacrit.cardcrawl.vfx.stance.CalmParticleEffect;

public class BloodlustParticleEffect extends AbstractStanceParticleEffect {
    public BloodlustParticleEffect() {
        super(new Color(MathUtils.random(0.8F, 1.0F), MathUtils.random(0.0F, 0.4F), MathUtils.random(0.0F, 0.3F), 0.0F),
                ImageMaster.GLOW_SPARK_2);

    }

}
