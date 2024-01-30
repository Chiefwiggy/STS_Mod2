package dkSTS.cards.Powers.Uncommon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.cards.Helpers.ApplyPowerBuilder;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;
import dkSTS.powers.HastePower;

public class Haste extends AbstractBruxaCard  {
    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(Haste.class)
            .img("Haste.png")
            .rarity(CardRarity.UNCOMMON)
            .type(CardType.POWER)
            .target(CardTarget.SELF)
            .cost(2)
            .build();

    private static final int DAZED = 2;

    public Haste() {
        super(data);

        baseMagicNumber = magicNumber = DAZED;
    }

    @Override
    protected void UpgradeParameters() {
        upgradeBaseCost(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        new ApplyPowerBuilder()
                .power(HastePower.class)
                .amount(1)
                .addToBottom();
    }
}
