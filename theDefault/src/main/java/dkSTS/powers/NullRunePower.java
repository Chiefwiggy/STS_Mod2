package dkSTS.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import dkSTS.powers.AbstractRune;
import dkSTS.powers.Helpers.PowerData;
import dkSTS.powers.Helpers.PowerDataBuilder;

public class NullRunePower extends AbstractRune {

    public static PowerData data = new PowerDataBuilder()
            .id(NullRunePower.class)
            .img_path("placeholder_power")
            .debuff()
            .build();

    public NullRunePower(final AbstractCreature owner, final AbstractCreature source) {
        super(data, owner, source, 0);
    }
    @Override
    public void whenRuneResolves() {
        this.addToBot(
                new RemoveSpecificPowerAction(owner, owner, StrengthPower.POWER_ID)
        );
    }

    @Override
    public AbstractPower makeCopy() {
        return new NullRunePower(owner, source);
    }

    @Override
    public void updateDescription() {
        description = DESC[0] + SwitchPlural(1, 2, true) + DESC[3];
    }
}
