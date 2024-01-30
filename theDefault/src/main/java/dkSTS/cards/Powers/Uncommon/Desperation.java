package dkSTS.cards.Powers.Uncommon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.cards.Helpers.ApplyPowerBuilder;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;
import dkSTS.powers.BloodPower;
import dkSTS.powers.DesperationPower;

public class Desperation extends AbstractBruxaCard {
    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(Desperation.class)
            .img("Desperation.png")
            .type(CardType.POWER)
            .target(CardTarget.SELF)
            .rarity(CardRarity.UNCOMMON)
            .cost(2)
            .build();


    private static final int HUNGER_UPGRADE = 3;
    public Desperation() {
        super(data);

        baseMagicNumber = magicNumber = 0;
    }

    @Override
    protected void UpgradeParameters() {
        UpgradeDescription();
        upgradeMagicNumber(HUNGER_UPGRADE);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        new ApplyPowerBuilder()
                .power(DesperationPower.class)
                .amount(1)
                .addToBottom();

        if (upgraded) {
            new ApplyPowerBuilder()
                    .power(BloodPower.class)
                    .amount(magicNumber)
                    .addToBottom();
        }
    }
}
