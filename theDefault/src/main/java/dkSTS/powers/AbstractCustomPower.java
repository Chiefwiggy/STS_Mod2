package dkSTS.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dkSTS.DefaultMod;
import dkSTS.powers.Helpers.PowerData;
import dkSTS.powers.Helpers.PowerDataBuilder;
import dkSTS.util.TextureLoader;

import java.util.logging.Logger;

public abstract class AbstractCustomPower extends AbstractPower implements CloneablePowerInterface {

    public AbstractCreature source;
    public AbstractCustomPower(PowerData dataBuilder, final AbstractCreature owner, final AbstractCreature source, final int amount) {
        PowerData data = dataBuilder;
        DESCRIPTIONS = data.DESCRIPTIONS;

        name = data.NAME;
        ID = data.POWER_ID;

        this.owner = owner;
        this.amount = amount;
        this.source = source;

        this.type = data.TYPE;
        isTurnBased = data.TURN_BASED;


        this.region128 = new TextureAtlas.AtlasRegion(data.tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(data.tex32, 0, 0, 32, 32);

        updateDescription();
    }

    public static String[] GetDescription() {
        return DESCRIPTIONS;
    }

    protected String SwitchPlural(int dindex_solo, int dindex_multi, boolean multiAmountPrefix) {
        if (this.amount == 1) {
            return DESCRIPTIONS[dindex_solo];
        } else {
            if (multiAmountPrefix) {
                return amount + DESCRIPTIONS[dindex_multi];
            }
            else {
                return DESCRIPTIONS[dindex_multi];
            }
        }
    }

    protected String SwitchPlural(int dindex_solo, int dindex_multi) {
        return SwitchPlural(dindex_solo, dindex_multi, false);
    }





}
