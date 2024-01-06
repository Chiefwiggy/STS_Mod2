package dkSTS.cards.Powers.Uncommon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.cards.Helpers.ApplyPowerBuilder;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;
import dkSTS.powers.DesperationPower;

public class Desperation extends AbstractBruxaCard {
    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(Desperation.class)
            .img("Power.png")
            .type(CardType.POWER)
            .target(CardTarget.SELF)
            .rarity(CardRarity.UNCOMMON)
            .cost(2)
            .build();

    public Desperation() {
        super(data);
    }

    @Override
    protected void UpgradeParameters() {
        upgradeBaseCost(1);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        new ApplyPowerBuilder()
                .power(DesperationPower.class)
                .amount(1)
                .addToBottom();
    }
}
