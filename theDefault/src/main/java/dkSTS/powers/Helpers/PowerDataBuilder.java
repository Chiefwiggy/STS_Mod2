package dkSTS.powers.Helpers;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dkSTS.DefaultMod;
import dkSTS.util.TextureLoader;

public class PowerDataBuilder {

    private PowerData pd;

    public PowerDataBuilder() {
        pd = new PowerData();
    }

    public PowerDataBuilder id(Class<?> clazz) {
        pd.POWER_ID = DefaultMod.makeID(clazz.getSimpleName());
        return this;
    }

    public PowerDataBuilder img_path(String path) {
        pd.IMG_PATH = path;
        return this;
    }

    public PowerDataBuilder buff() {
        pd.TYPE = AbstractPower.PowerType.BUFF;
        return this;
    }

    public PowerDataBuilder debuff() {
        pd.TYPE = AbstractPower.PowerType.DEBUFF;
        return this;
    }

    public PowerDataBuilder turnBased() {
        pd.TURN_BASED = true;
        return this;
    }

    public PowerDataBuilder notTurnBased() {
        pd.TURN_BASED = false;
        return this;
    }

    public PowerData build() {

        pd.tex32 = TextureLoader.getTexture(DefaultMod.makePowerPath(pd.IMG_PATH+"32.png"));
        pd.tex84 = TextureLoader.getTexture(DefaultMod.makePowerPath(pd.IMG_PATH+"84.png"));

        PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(pd.POWER_ID);

        pd.NAME = powerStrings.NAME;
        pd.DESCRIPTIONS = powerStrings.DESCRIPTIONS;

        return pd;
    }
}
