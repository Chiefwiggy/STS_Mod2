package dkSTS.cards.Powers.Uncommon;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.cards.Helpers.ApplyPowerBuilder;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;
import dkSTS.powers.FeralPower;

public class InspiringFerocity extends AbstractBruxaCard {
    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(InspiringFerocity.class)
            .img("LupineMutagen.png")
            .target(AbstractCard.CardTarget.SELF)
            .type(AbstractCard.CardType.POWER)
            .rarity(AbstractCard.CardRarity.UNCOMMON)
            .cost(1)
            .build();

    private static final int FERAL_GAIN = 1;
    private static final int FERAL_UPGRADE = 1;

    public InspiringFerocity() {
        super(data);

        baseMagicNumber = magicNumber = FERAL_GAIN;
    }

    @Override
    protected void UpgradeParameters() {
        upgradeMagicNumber(FERAL_UPGRADE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        new ApplyPowerBuilder()
                .power(FeralPower.class)
                .amount(magicNumber)
                .addToBottom();
    }
}
