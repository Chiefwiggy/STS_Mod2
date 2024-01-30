package dkSTS.events.Helpers;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import dkSTS.DefaultMod;

public class AscensionEventHPVariable extends AscensionEventVariable<Float> {

    public AscensionEventHPVariable() {
        super();
    }

    public AscensionEventHPVariable(final float base, final float a15) {
        this();
        setBase(base);
        setA15(a15);
    }


    public int getHP(AbstractPlayer p) {
        float percent = get();
        DefaultMod.logger.warn("XXXXXXXXXXXXXXXXXXX " + percent + " : " + percent * p.maxHealth);
        return MathUtils.ceil(p.maxHealth * percent);
    }

    public float getRaw() {
        return super.get();
    }
}
