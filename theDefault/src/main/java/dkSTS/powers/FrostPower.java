package dkSTS.powers;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import dkSTS.DefaultMod;
import dkSTS.powers.Helpers.PowerData;
import dkSTS.powers.Helpers.PowerDataBuilder;

public class FrostPower extends AbstractCountdownPower {

    public static PowerData data = new PowerDataBuilder()
            .id(FrostPower.class)
            .img_path("placeholder_power")
            .debuff()
            .build();
    public FrostPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        super(data, owner, source, amount);

        this.priority = 100;
    }
    @Override
    public AbstractPower makeCopy() {
        return new FrostPower(owner, source, amount);
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + SwitchPlural(1, 2);
    }


    public float atDamageGive(float damage, DamageType type) {
        if (type == DamageType.NORMAL) {
            return damage * 0.9F;
        } else {
            return damage;
        }
    }

    @Override
    public float atDamageReceive(float damage, DamageType damageType) {
        if (damageType == DamageType.NORMAL) {
            return damage * 1.1F;
        } else {
            return damage;
        }
    }



}
