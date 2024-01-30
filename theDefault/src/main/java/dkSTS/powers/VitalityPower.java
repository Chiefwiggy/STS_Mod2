package dkSTS.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dkSTS.powers.Helpers.PowerData;
import dkSTS.powers.Helpers.PowerDataBuilder;

public class VitalityPower extends AbstractCustomPower {
    public static PowerData data = new PowerDataBuilder()
            .id(VitalityPower.class)
            .img_path("vitality")
            .notTurnBased()
            .buff()
            .build();

    public VitalityPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        super(data, owner, source, amount);
    }

    @Override
    public AbstractPower makeCopy() {
        return new VitalityPower(owner, source, amount);
    }

    @Override
    public void updateDescription() {
        description = DESC[0] + this.amount + DESC[1];
    }
}
