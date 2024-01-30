package dkSTS.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dkSTS.powers.Helpers.PowerData;
import dkSTS.powers.Helpers.PowerDataBuilder;

public class RimefrostRunePower extends AbstractRune {

    public static PowerData data = new PowerDataBuilder()
            .id(RimefrostRunePower.class)
            .img_path("frozenhex")
            .debuff()
            .build();

    public RimefrostRunePower(final AbstractCreature owner, final AbstractCreature source) {
        super(data, owner, source, 0);


    }

    @Override
    public void updateDescription() {
        description = DESC[0] + SwitchPlural(1, 2, true) + DESC[3];
    }

    @Override
    public void whenRuneResolves() {
        this.addToTop(
                new ApplyPowerAction(
                        owner, owner, new FrozenPower(owner, owner, 1), 1
                )
        );
    }

    @Override
    public AbstractPower makeCopy() {
        return new RimefrostRunePower(owner, source);
    }
}
