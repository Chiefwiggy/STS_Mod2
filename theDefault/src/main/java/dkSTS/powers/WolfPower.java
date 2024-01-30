package dkSTS.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dkSTS.powers.Helpers.PowerData;
import dkSTS.powers.Helpers.PowerDataBuilder;

public class WolfPower extends AbstractCustomPower{
    public static PowerData data = new PowerDataBuilder()
            .id(WolfPower.class)
            .img_path("wolf")
            .notTurnBased()
            .buff()
            .build();

    public WolfPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        super(data, owner, source, amount);
    }

    @Override
    public AbstractPower makeCopy() {
        return new WolfPower(owner, source, amount);
    }

    @Override
    public void updateDescription() {
        description = DESC[0];
    }
}
