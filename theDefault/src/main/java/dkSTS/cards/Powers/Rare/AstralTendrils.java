package dkSTS.cards.Powers.Rare;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ConstrictedPower;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.cards.HelperCards.EchoTendril;
import dkSTS.cards.Helpers.ApplyPowerBuilder;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;
import dkSTS.powers.EchoesOfTheVoidPower;

public class AstralTendrils extends AbstractBruxaCard {
    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(AstralTendrils.class)
            .img("AstrilTendrils.png")
            .type(CardType.POWER)
            .rarity(CardRarity.RARE)
            .target(CardTarget.SELF)
            .cost(2)
            .build();

    private static final int SELF_DAMAGE = 2;

    public AstralTendrils() {
        super(data);

        cardsToPreview = new EchoTendril();
        baseMagicNumber = magicNumber = SELF_DAMAGE;
    }

    @Override
    protected void UpgradeParameters() {
        upgradeBaseCost(1);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        new ApplyPowerBuilder()
                .power(EchoesOfTheVoidPower.class)
                .amount(1)
                .addToBottom();

        new ApplyPowerBuilder()
                .power(ConstrictedPower.class)
                .amount(magicNumber)
                .addToBottom();
    }
}
