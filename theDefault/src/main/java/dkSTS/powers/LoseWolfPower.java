package dkSTS.powers;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dkSTS.cards.Helpers.ApplyPowerBuilder;
import dkSTS.powers.Helpers.PowerData;
import dkSTS.powers.Helpers.PowerDataBuilder;

public class LoseWolfPower extends AbstractCustomPower {
    public static PowerData data = new PowerDataBuilder()
            .id(LoseWolfPower.class)
            .img_path("lose_wolf")
            .debuff()
            .notTurnBased()
            .build();

    public LoseWolfPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        super(data, owner, source, amount);
    }

    @Override
    public AbstractPower makeCopy() {
        return new LoseWolfPower(owner, source, amount);
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        this.addToBot(
                new ReducePowerAction(owner, source, WolfPower.data.POWER_ID, amount)
        );
        this.addToBot(
                new RemoveSpecificPowerAction(owner, source, this)
        );
    }

    @Override
    public void updateDescription() {
        description = DESC[0] + amount + DESC[1];
    }
}
