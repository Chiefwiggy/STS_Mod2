package dkSTS.powers;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.powers.Helpers.PowerData;
import dkSTS.powers.Helpers.PowerDataBuilder;

public class ExposePower extends AbstractCustomPower {
    public static PowerData data = new PowerDataBuilder()
            .id(ExposePower.class)
            .img_path("placeholder_power")
            .debuff()
            .notTurnBased()
            .build();

    public ExposePower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        super(data, owner, source, amount);
    }


    @Override
    public AbstractPower makeCopy() {
        return new ExposePower(owner, source, amount);
    }

    @Override
    public float atDamageReceive(float damage, DamageInfo.DamageType damageType) {
        if (damageType == DamageInfo.DamageType.NORMAL) {
            return damage + amount;
        }
        return damage;
    }

    @Override
    public void updateDescription() {
        description = DESC[0] + amount + DESC[1];
    }
}
