package dkSTS.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dkSTS.powers.Helpers.PowerData;
import dkSTS.powers.Helpers.PowerDataBuilder;

public class RewindPower extends AbstractCustomPower {
    public static PowerData data = new PowerDataBuilder()
            .id(RewindPower.class)
            .img_path("placeholder_power")
            .buff()
            .notTurnBased()
            .build();

    public RewindPower(final AbstractCreature owner, final AbstractCreature source) {
        super(data, owner, source, -1);
    }

    @Override
    public AbstractPower makeCopy() {
        return new RewindPower(owner, source);
    }

    @Override
    public void updateDescription() {
        description = DESC[0];
    }
}
