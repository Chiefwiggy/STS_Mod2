package dkSTS.powers;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import dkSTS.powers.Helpers.PowerData;
import dkSTS.powers.Helpers.PowerDataBuilder;

public class SimplementsPower extends AbstractCustomPower {
    public static PowerData data = new PowerDataBuilder()
            .id(SimplementsPower.class)
            .img_path("simplements")
            .notTurnBased()
            .buff()
            .build();


    public SimplementsPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        super(data, owner, source, amount);
    }


    @Override
    public AbstractPower makeCopy() {
        return new SimplementsPower(owner, source, amount);
    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type, AbstractCard card) {
        if ((card.rarity == AbstractCard.CardRarity.COMMON || card.rarity == AbstractCard.CardRarity.BASIC) && type == DamageInfo.DamageType.NORMAL) {
            return damage + amount;
        }
        return super.atDamageGive(damage, type, card);
    }

    @Override
    public void updateDescription() {
        description = DESC[0] + amount + DESC[1];
    }
}
