package dkSTS.cards.Powers.Rare;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.cards.Helpers.ApplyPowerBuilder;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;
import dkSTS.powers.DancingStarsPower;

public class DancingStars extends AbstractBruxaCard {
    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(DancingStars.class)
            .img("ShootingStars.png")
            .rarity(CardRarity.RARE)
            .target(CardTarget.SELF)
            .type(CardType.POWER)
            .cost(2)
            .build();

    private static final int STARS = 2;
    private static final int STARS_UPGRADE = 1;

    public DancingStars() {
        super(data);

        baseMagicNumber = magicNumber = STARS;
    }

    @Override
    protected void UpgradeParameters() {
        upgradeMagicNumber(STARS_UPGRADE);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        new ApplyPowerBuilder()
                .power(DancingStarsPower.class)
                .amount(magicNumber)
                .addToBottom();
    }
}
