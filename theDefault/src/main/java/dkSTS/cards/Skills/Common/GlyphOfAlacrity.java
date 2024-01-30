package dkSTS.cards.Skills.Common;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.EnergizedPower;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.cards.Helpers.ApplyPowerBuilder;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;

public class GlyphOfAlacrity extends AbstractBruxaCard {
    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(GlyphOfAlacrity.class)
            .img("GlyphOfAlacrity.png")
            .rarity(CardRarity.COMMON)
            .type(CardType.SKILL)
            .cost(1)
            .target(CardTarget.SELF)
            .build();

    private static final int ENERGY_GAIN = 2;
    private static final int ENERGY_UPGRADE = 1;

    public GlyphOfAlacrity() {
        super(data);
        baseMagicNumber = magicNumber = ENERGY_GAIN;

        selfRetain = true;
    }

    @Override
    protected void UpgradeParameters() {
        UpgradeDescription();
        upgradeMagicNumber(ENERGY_UPGRADE);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        new ApplyPowerBuilder()
                .power(EnergizedPower.class)
                .amount(magicNumber)
                .addToBottom();
    }
}
