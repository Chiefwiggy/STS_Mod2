package dkSTS.powers;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.BufferPower;
import dkSTS.powers.Helpers.PowerData;
import dkSTS.powers.Helpers.PowerDataBuilder;

public class CloakPower extends AbstractCustomPower{
    public static PowerData data = new PowerDataBuilder()
            .id(CloakPower.class)
            .img_path("placeholder_power")
            .notTurnBased()
            .buff()
            .build();

    public CloakPower(final AbstractCreature owner, final AbstractCreature source, int amount) {
        super(data, owner, source, amount);

        priority = 99;
    }

    @Override
    public AbstractPower makeCopy() {
        return new CloakPower(owner, source, amount);
    }

    @Override
    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
        if (damageAmount > 0) {
            this.addToTop(
                    new ReducePowerAction(owner, owner, this.ID, 1)
            );
        }
        return 0;
    }


    @Override
    public void atStartOfTurn() {
        this.addToBot(
                new RemoveSpecificPowerAction(owner, owner, this)
        );
    }

    @Override
    public void updateDescription() {
        description = DESC[0] + SwitchPlural(1, 2, true) + DESC[3] ;
    }
}
