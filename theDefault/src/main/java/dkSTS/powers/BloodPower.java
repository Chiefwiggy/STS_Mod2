package dkSTS.powers;

import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dkSTS.cards.Helpers.DamageActionBuilder;
import dkSTS.powers.Helpers.PowerData;
import dkSTS.powers.Helpers.PowerDataBuilder;
import dkSTS.stances.BloodlustStance;

public class BloodPower extends AbstractCustomPower {
    public static PowerData data = new PowerDataBuilder()
            .id(BloodPower.class)
            .img_path("hunger")
            .buff()
            .notTurnBased()
            .build();


    private final static int BLOOD_NEEDED = 5;


    public BloodPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        super(data, owner, source, amount);
    }

    @Override
    public AbstractPower makeCopy() {
        return new BloodPower(owner, source, amount);
    }

    @Override
    public void updateDescription() {
        description = DESC[0] + this.amount + DESC[1];
    }

    @Override
    public void atStartOfTurn() {
        if (this.amount >= BLOOD_NEEDED) {
            addToBot(
                    new RemoveSpecificPowerAction(owner, source, this)
            );
            addToBot(new ChangeStanceAction(new BloodlustStance()));

        } else {
            new DamageActionBuilder()
                    .damage(this.amount)
                    .target(this.owner)
                    .damageType(DamageInfo.DamageType.HP_LOSS)
                    .animation(AbstractGameAction.AttackEffect.POISON)
                    .addToBottom();

            this.addToBot(
                    new AddTemporaryHPAction(this.owner, this.owner, this.amount)
            );
        }
    }
}
