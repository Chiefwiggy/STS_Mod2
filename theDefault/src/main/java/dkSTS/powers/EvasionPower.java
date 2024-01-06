package dkSTS.powers;

import basemod.abstracts.CustomSavable;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.blue.Consume;
import com.megacrit.cardcrawl.cards.red.Feed;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.potions.FairyPotion;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.GremlinHorn;
import dkSTS.DefaultMod;
import dkSTS.powers.Helpers.PowerData;
import dkSTS.powers.Helpers.PowerDataBuilder;
import dkSTS.relics.AlacrityRuneRelic;
import dkSTS.util.EvasionCounter;
import dkSTS.util.EvasionSubscriber;

public class EvasionPower extends AbstractCustomPower implements EvasionSubscriber {

    public static PowerData data = new PowerDataBuilder()
            .id(EvasionPower.class)
            .img_path("placeholder_power")
            .notTurnBased()
            .buff()
            .build();

    private int timesBlocked;
    public EvasionPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        super(data, owner, source, amount);
        timesBlocked = 0;

        EvasionCounter.RegisterSubscriber(this);
    }

    @Override
    public void onRemove() {
        EvasionCounter.DeregisterSubscriber(this);
    }

    @Override
    public AbstractPower makeCopy() {
        return new EvasionPower(owner, source, amount);
    }

    @Override
    public void updateDescription() {
        description = DESC[0] + this.SwitchPlural(1, 2, true) + DESC[3] +
                EvasionCounter.GetCurrentEvasion() + DESC[4];
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {

        AbstractPlayer p = AbstractDungeon.player;

        if (info.owner != null && info.type == DamageInfo.DamageType.NORMAL && damageAmount >= 1 && damageAmount <= EvasionCounter.GetCurrentEvasion()) {
            this.flash();
            timesBlocked++;
            if (timesBlocked >= this.amount) {
                this.addToTop(new RemoveSpecificPowerAction(p, p, EvasionPower.data.POWER_ID));
            } else {
                this.addToTop(new ReducePowerAction(p, p, EvasionPower.data.POWER_ID, timesBlocked));
                timesBlocked = 0;
            }
            return MathUtils.floor(damageAmount * 0.5F);
        } else {
            return damageAmount;
        }
    }

    @Override
    public void receiveEvasionModified(int newValue) {
        updateDescription();
    }
}
