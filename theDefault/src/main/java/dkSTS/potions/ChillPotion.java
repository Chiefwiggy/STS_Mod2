package dkSTS.potions;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.potions.WeakenPotion;
import dkSTS.cards.Helpers.ApplyPowerBuilder;
import dkSTS.potions.Helpers.PotionData;
import dkSTS.potions.Helpers.PotionDataBuilder;
import dkSTS.powers.FrostPower;

public class ChillPotion extends AbstractCustomPotion {
    public static PotionData data = new PotionDataBuilder()
            .id(ChillPotion.class)
            .combatOnly()
            .thrown()
            .color_enum(PotionColor.SMOKE)
            .size(PotionSize.M)
            .rarity(PotionRarity.COMMON)
            .liquid(0,0,255f)
            .hybrid(35, 35, 212)
            .spots(0,33,128)
            .potency(3)
            .build();

    public ChillPotion() {
        super(data);
    }


    @Override
    public String setDescription() {
        return DESC[0] + getPotency() + DESC[1];
    }

    @Override
    public void use(AbstractCreature c) {
        new ApplyPowerBuilder()
                .power(FrostPower.class)
                .amount(getPotency())
                .target((AbstractMonster)c)
                .addToBottom();
    }

    @Override
    public AbstractPotion makeCopy() {
        return new ChillPotion();
    }

}
