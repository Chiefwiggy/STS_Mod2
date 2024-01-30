package dkSTS.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dkSTS.DefaultMod;
import dkSTS.powers.Helpers.PowerData;

import java.util.ArrayList;

public abstract class AbstractCustomPower extends AbstractPower implements CloneablePowerInterface {

    public AbstractCreature source;
    public String[] DESC;

    public int amount2 = -1;
    public boolean canGoNegative2 = false;
    public boolean showZero2 = false;
    protected Color redColor2;
    protected Color greenColor2;

    public AbstractCustomPower(PowerData dataBuilder, final AbstractCreature owner, final AbstractCreature source, final int amount) {
        DESC = dataBuilder.DESCRIPTIONS;
        name = dataBuilder.NAME;
        ID = dataBuilder.POWER_ID;

        this.owner = owner;
        this.amount = amount;
        this.source = source;

        this.type = dataBuilder.TYPE;
        isTurnBased = dataBuilder.TURN_BASED;

        this.redColor2 = Color.RED.cpy();
        this.greenColor2 = Color.WHITE.cpy();


        this.region128 = new TextureAtlas.AtlasRegion(dataBuilder.tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(dataBuilder.tex32, 0, 0, 32, 32);

        updateDescription();
    }


    public void renderAmount(SpriteBatch sb, float x, float y, Color c) {
        super.renderAmount(sb, x, y, c);
        if (this.amount2 > 0) {
            if (!this.isTurnBased) {
                this.greenColor2.a = c.a;
                c = this.greenColor2;
            }

            FontHelper.renderFontRightTopAligned(sb, FontHelper.powerAmountFont, Integer.toString(this.amount2), x, y + 15.0F * Settings.scale, this.fontScale, c);
        } else if (this.amount2 < 0 && this.canGoNegative2) {
            this.redColor2.a = c.a;
            c = this.redColor2;
            FontHelper.renderFontRightTopAligned(sb, FontHelper.powerAmountFont, Integer.toString(this.amount2), x, y + 15.0F * Settings.scale, this.fontScale, c);
        } else if (showZero2) {
            this.redColor2.a = c.a;
            c = this.redColor2;
            FontHelper.renderFontRightTopAligned(sb, FontHelper.powerAmountFont, Integer.toString(0), x, y + 15.0F * Settings.scale, this.fontScale, c);
        }

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

    public void stackPower2(int stackAmount2) {
        if (this.amount2 == -1) {
            DefaultMod.logger.info(this.name + " does not stack");
        } else {
            this.fontScale = 8.0F;
            this.amount2 += stackAmount2;
        }
        DefaultMod.logger.info("XXXXXXXXXXXXXXXXXXXXXXXXXX sa2 " + stackAmount2);
    }

    public void onMonsterTrueDeath(){};
    public void onCardRedraw(AbstractCard c){};

    public void atEndOfBattle(){}


}
