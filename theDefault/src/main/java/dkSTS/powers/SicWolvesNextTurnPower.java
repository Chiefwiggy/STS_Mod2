package dkSTS.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dkSTS.actions.SicWolvesAction;
import dkSTS.powers.Helpers.PowerData;
import dkSTS.powers.Helpers.PowerDataBuilder;

public class SicWolvesNextTurnPower extends AbstractCustomPower {
    public static PowerData data = new PowerDataBuilder()
            .id(SicWolvesNextTurnPower.class)
            .img_path("placeholder_power")
            .buff()
            .notTurnBased()
            .build();

    public SicWolvesNextTurnPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        super(data, owner, source, amount);
    }

    @Override
    public void atStartOfTurn() {
        for (int i = 0; i < amount; ++i) {
            this.addToBot(
                    new SicWolvesAction()
            );
        }
        this.RemoveSelf();
    }

    @Override
    public AbstractPower makeCopy() {
        return new SicWolvesNextTurnPower(owner, source, amount);
    }

    @Override
    public void updateDescription() {
        description = DESC[0] + SwitchPlural(1, 2, true);
    }
}
