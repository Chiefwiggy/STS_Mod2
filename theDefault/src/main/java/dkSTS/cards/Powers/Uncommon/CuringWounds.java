package dkSTS.cards.Powers.Uncommon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.cards.Helpers.ApplyPowerBuilder;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;
import dkSTS.powers.CureWoundsPower;

public class CuringWounds extends AbstractBruxaCard {
    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(CuringWounds.class)
            .img("CuringWounds.png")
            .type(CardType.POWER)
            .cost(2)
            .rarity(CardRarity.UNCOMMON)
            .target(CardTarget.SELF)
            .build();

    public CuringWounds() {
        super(data);
    }

    @Override
    protected void UpgradeParameters() {
        upgradeBaseCost(1);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        new ApplyPowerBuilder()
                .power(CureWoundsPower.class)
                .amount(1)
                .addToBottom();
    }
}
