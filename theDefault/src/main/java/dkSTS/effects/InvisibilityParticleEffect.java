package dkSTS.effects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.helpers.ImageMaster;

public class InvisibilityParticleEffect extends AbstractStanceParticleEffect {
    public InvisibilityParticleEffect() {
        super(
                new Color(
                        MathUtils.random(0.0F, 0.6F),
                        MathUtils.random(0.0F, 0.6F),
                        MathUtils.random(0.0F, 0.6F),
                        0.5F
                ),
                ImageMaster.DUST_5
        );
    }
}
