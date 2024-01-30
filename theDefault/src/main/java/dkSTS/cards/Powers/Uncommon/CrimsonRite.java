package dkSTS.cards.Powers.Uncommon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.cards.Helpers.ApplyPowerBuilder;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;
import dkSTS.powers.CrimsonRitePower;

public class CrimsonRite extends AbstractBruxaCard {
    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(CrimsonRite.class)
            .img("CrimsonRite.png")
            .target(CardTarget.NONE)
            .type(CardType.POWER)
            .rarity(CardRarity.UNCOMMON)
            .cost(1)
            .build();

    private static final int REGEN = 1;
    private static final int REGEN_UPGRADE = 1;

    public CrimsonRite() {
        super(data);
        baseMagicNumber = magicNumber = REGEN;
    }

    @Override
    protected void UpgradeParameters() {
        upgradeMagicNumber(REGEN_UPGRADE);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        new ApplyPowerBuilder()
                .power(CrimsonRitePower.class)
                .amount(magicNumber)
                .addToBottom();
    }
}
