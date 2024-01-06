package dkSTS.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dkSTS.powers.Helpers.PowerData;
import dkSTS.powers.Helpers.PowerDataBuilder;

public abstract class AbstractInstancedPower extends AbstractCountdownPower {
    private static int idOffset = 0;

    public AbstractInstancedPower(PowerData dataBuilder, AbstractCreature owner, AbstractCreature source, int amount) {
        super(dataBuilder, owner, source, amount);
        ID = dataBuilder.POWER_ID + idOffset;
        idOffset++;
    }


}
