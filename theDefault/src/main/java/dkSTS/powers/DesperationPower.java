package dkSTS.powers;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import dkSTS.cards.Helpers.DamageActionBuilder;
import dkSTS.cards.Powers.Uncommon.Desperation;
import dkSTS.powers.Helpers.PowerData;
import dkSTS.powers.Helpers.PowerDataBuilder;

public class DesperationPower extends AbstractCustomPower {
    public static PowerData data = new PowerDataBuilder()
            .id(DesperationPower.class)
            .img_path("placeholder_power")
            .buff()
            .notTurnBased()
            .build();

    public DesperationPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        super(data, owner, source, amount);
    }


    @Override
    public AbstractPower makeCopy() {
        return new DesperationPower(owner, source, amount);
    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type, AbstractCard card) {
        if (owner.hasPower(BloodPower.data.POWER_ID)) {
            int extra = owner.getPower(BloodPower.data.POWER_ID).amount;
            return type == DamageInfo.DamageType.NORMAL ? (float)(damage + (extra * this.amount)) : damage;
        }
        return damage;
    }

    @Override
    public void updateDescription() {
        description = DESC[0] + this.amount + DESC[1];
    }
}
