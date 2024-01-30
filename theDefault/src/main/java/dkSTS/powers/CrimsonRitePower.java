package dkSTS.powers;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.RegenPower;
import dkSTS.cards.Helpers.ApplyPowerBuilder;
import dkSTS.cards.Powers.Uncommon.CrimsonRite;
import dkSTS.powers.Helpers.PowerData;
import dkSTS.powers.Helpers.PowerDataBuilder;

public class CrimsonRitePower extends AbstractCustomPower {
    public static PowerData data = new PowerDataBuilder()
            .id(CrimsonRitePower.class)
            .img_path("placeholder_power")
            .buff()
            .notTurnBased()
            .build();

    public CrimsonRitePower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        super(data, owner, source, amount);

    }

    @Override
    public AbstractPower makeCopy() {
        return new CrimsonRitePower(owner, source, amount);
    }

    public void wasHPLost(DamageInfo info, int damageAmount) {
        if (damageAmount > 0 && info.owner == this.owner) {
            this.flash();
            new ApplyPowerBuilder()
                    .power(RegenPower.class)
                    .amount(amount)
                    .addToTop();
        }

    }

    @Override
    public void updateDescription() {
        description = DESC[0] + amount + DESC[1];
    }
}
