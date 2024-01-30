package dkSTS.cards.Powers.Rare;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.cards.Helpers.ApplyPowerBuilder;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;
import dkSTS.powers.SimplementsPower;

public class Simplements extends AbstractBruxaCard {
    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(Simplements.class)
            .img("Power.png")
            .target(CardTarget.SELF)
            .rarity(CardRarity.RARE)
            .type(CardType.POWER)
            .cost(2)
            .build();

    private static final int SIMPLE = 4;
    private static final int SIMPLE_UP = 1;

    public Simplements() {
        super(data);

        baseMagicNumber = magicNumber = SIMPLE;
    }


    @Override
    protected void UpgradeParameters() {
        upgradeMagicNumber(SIMPLE_UP);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        new ApplyPowerBuilder()
                .power(SimplementsPower.class)
                .amount(magicNumber)
                .addToBottom();
    }
}
