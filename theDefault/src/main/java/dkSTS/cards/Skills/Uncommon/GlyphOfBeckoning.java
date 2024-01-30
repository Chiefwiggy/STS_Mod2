package dkSTS.cards.Skills.Uncommon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.cards.Helpers.ApplyPowerBuilder;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;
import dkSTS.cards.Skills.Common.GlyphOfAlacrity;
import dkSTS.powers.SicWolvesNextTurnPower;

public class GlyphOfBeckoning extends AbstractBruxaCard {
    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(GlyphOfBeckoning.class)
            .img("GlyphOfBeckoning.png")
            .type(CardType.SKILL)
            .rarity(CardRarity.UNCOMMON)
            .cost(1)
            .target(CardTarget.SELF)
            .build();

    private static final int SIC = 1;
    private static final int SIC_UPGRADE = 1;

    public GlyphOfBeckoning() {
        super(data);
        baseMagicNumber = magicNumber = SIC;
    }

    @Override
    protected void UpgradeParameters() {
        UpgradeDescription();
        upgradeMagicNumber(SIC_UPGRADE);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        new ApplyPowerBuilder()
                .amount(magicNumber)
                .power(SicWolvesNextTurnPower.class)
                .addToBottom();
    }
}
