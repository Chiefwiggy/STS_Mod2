package dkSTS.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dkSTS.DefaultMod;
import dkSTS.powers.Helpers.PowerData;
import dkSTS.powers.Helpers.PowerDataBuilder;
import dkSTS.util.TextureLoader;

import java.util.logging.Logger;

public abstract class AbstractCustomPower extends AbstractPower implements CloneablePowerInterface {

    public AbstractCreature source;
    public String[] DESC;
    public AbstractCustomPower(PowerData dataBuilder, final AbstractCreature owner, final AbstractCreature source, final int amount) {
        DESC = dataBuilder.DESCRIPTIONS;
        name = dataBuilder.NAME;
        ID = dataBuilder.POWER_ID;

        this.owner = owner;
        this.amount = amount;
        this.source = source;

        this.type = dataBuilder.TYPE;
        isTurnBased = dataBuilder.TURN_BASED;


        this.region128 = new TextureAtlas.AtlasRegion(dataBuilder.tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(dataBuilder.tex32, 0, 0, 32, 32);

        updateDescription();
    }

    protected String SwitchPlural(int dindex_solo, int dindex_multi, boolean multiAmountPrefix) {
        try {
            if (this.amount <= 1) {
                return DESC[dindex_solo];
            } else {
                if (multiAmountPrefix) {
                    return amount + DESC[dindex_multi];
                } else {
                    return DESC[dindex_multi];
                }
            }
        } catch (Exception e) {
            DefaultMod.logger.error(e);
            return "";
        }
    }

    protected String SwitchPlural(int dindex_solo, int dindex_multi) {
        return SwitchPlural(dindex_solo, dindex_multi, false);
    }

    protected <T extends AbstractGameEffect> void addAnimation(Class<T> effectClass, Object... otherArguments) {
        if (this.owner != null) {
            float duration = Settings.FAST_MODE ? 0.1F : 0.3F;
            try {
                T effectInstance = effectClass.getDeclaredConstructor(float.class, float.class)
                        .newInstance(owner.hb.cX, owner.hb.cY - 40.0F * Settings.scale, otherArguments);
                this.addToBot(new VFXAction(effectInstance, duration));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected void RemoveSelf() {
        AbstractPlayer p = AbstractDungeon.player;
        addToBot(
                new RemoveSpecificPowerAction(p, p, this)
        );
    }


    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        updateDescription();
    }

    public void onMonsterTrueDeath(){};
}
