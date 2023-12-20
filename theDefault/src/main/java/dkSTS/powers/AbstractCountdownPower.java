package dkSTS.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import dkSTS.powers.Helpers.PowerData;
import dkSTS.powers.Helpers.PowerDataBuilder;

public abstract class AbstractCountdownPower extends AbstractCustomPower {

    public AbstractCountdownPower(PowerData data, AbstractCreature owner, AbstractCreature source, int amount) {
        super(data, owner, source, amount);

    }

    @Override
    public void atEndOfRound() {
        super.atEndOfRound();

        this.reducePower(1);

        if (amount == 0) {
            this.addToBot(
                    new RemoveSpecificPowerAction(this.owner, this.owner, this)
            );
        }

        updateDescription();
    }
}
