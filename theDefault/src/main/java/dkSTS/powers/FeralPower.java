package dkSTS.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dkSTS.powers.Helpers.PowerData;
import dkSTS.powers.Helpers.PowerDataBuilder;

public class FeralPower extends AbstractCustomPower {
    public static PowerData data = new PowerDataBuilder()
            .id(FeralPower.class)
            .img_path("feral")
            .buff()
            .build();

    public FeralPower(final AbstractCreature owner, final int amount) {
        super(data, owner, owner, amount);
    }

    @Override
    public AbstractPower makeCopy() {
        return new FeralPower(owner, amount);
    }

    @Override
    public void updateDescription() {
        description = DESC[0] + amount + DESC[1];
    }
}
