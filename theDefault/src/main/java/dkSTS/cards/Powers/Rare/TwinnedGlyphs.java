package dkSTS.cards.Powers.Rare;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.cards.Helpers.ApplyPowerBuilder;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;
import dkSTS.powers.ImpatientPower;

public class TwinnedGlyphs extends AbstractBruxaCard {
    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(TwinnedGlyphs.class)
            .img("Power.png")
            .type(CardType.POWER)
            .rarity(CardRarity.RARE)
            .target(CardTarget.SELF)
            .cost(2)
            .build();

    private static final int TIMES = 1;
    private static final int TIMES_UPGRADE = 1;

    public TwinnedGlyphs() {
        super(data);
        baseMagicNumber = magicNumber = 1;
    }

    @Override
    protected void UpgradeParameters() {
        upgradeMagicNumber(TIMES_UPGRADE);
        UpgradeDescription();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        new ApplyPowerBuilder()
                .power(ImpatientPower.class)
                .amount(magicNumber)
                .addToBottom();

    }
}
