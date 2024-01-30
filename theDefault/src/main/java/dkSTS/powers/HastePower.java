package dkSTS.powers;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import dkSTS.powers.Helpers.PowerData;
import dkSTS.powers.Helpers.PowerDataBuilder;

public class HastePower extends AbstractCustomPower{
    public static PowerData data = new PowerDataBuilder()
            .id(HastePower.class)
            .img_path("placeholder_power")
            .buff()
            .notTurnBased()
            .build();

    public HastePower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        super(data, owner, source, amount);
    }

    @Override
    public AbstractPower makeCopy() {
        return new HastePower(owner, source, amount);
    }

    @Override
    public void atStartOfTurnPostDraw() {
        EnergyPanel.addEnergy(amount);
        this.addToBot(
                new DrawCardAction(amount)
        );
        for (int i = 0; i < amount; ++i) {
            this.addToBot(
                    new MakeTempCardInDiscardAction(new Dazed(), 2)
            );
        }

    }

    @Override
    public void updateDescription() {
        StringBuilder enString = new StringBuilder();
        if (amount > 3) {
            enString = new StringBuilder(amount + DESC[2]);
        } else {
            for (int i = 0; i < amount; ++i) {
                enString.append(DESC[1]);
            }
        }
        description = DESC[0] + enString + DESC[3] + this.amount + SwitchPlural(4, 5) + DESC[6] + this.amount*2 + DESC[7];
    }
}
