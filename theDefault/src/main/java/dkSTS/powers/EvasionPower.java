package dkSTS.powers;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dkSTS.powers.Helpers.PowerData;
import dkSTS.powers.Helpers.PowerDataBuilder;

public class EvasionPower extends AbstractCustomPower {

    public static PowerData data = new PowerDataBuilder()
            .id(EvasionPower.class)
            .img_path("placeholder_power")
            .notTurnBased()
            .buff()
            .build();

    private int timesBlocked;
    private static int EVADE_CAP = 10;
    public EvasionPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        super(data, owner, source, amount);
        timesBlocked = 0;
    }

    @Override
    public AbstractPower makeCopy() {
        return new EvasionPower(owner, source, amount);
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + this.SwitchPlural(1, 2, true) + DESCRIPTIONS[3];
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {

        AbstractPlayer p = AbstractDungeon.player;

        if (info.owner != null && info.type == DamageInfo.DamageType.NORMAL && damageAmount >= 1 && damageAmount <= EVADE_CAP) {
            this.flash();
            timesBlocked++;
            if (timesBlocked >= this.amount) {
                this.addToTop(new RemoveSpecificPowerAction(p, p, EvasionPower.data.POWER_ID));
            } else {
                this.addToTop(new ReducePowerAction(p, p, EvasionPower.data.POWER_ID, timesBlocked));
                timesBlocked = 0;
            }
            return Math.round(damageAmount * 0.5F);
        } else {
            return damageAmount;
        }
    }
}
