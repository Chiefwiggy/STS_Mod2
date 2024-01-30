package dkSTS.cards.Attacks.Rare;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dkSTS.actions.SicWolvesAction;
import dkSTS.cards.Abstracts.AbstractFluxCard;
import dkSTS.cards.Helpers.ApplyPowerBuilder;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;
import dkSTS.powers.LoseWolfPower;
import dkSTS.powers.WolfPower;

public class NaturesAnarchy extends AbstractFluxCard {
    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(NaturesAnarchy.class)
            .img("NatureAnarchy.png")
            .target(CardTarget.ALL_ENEMY)
            .rarity(CardRarity.RARE)
            .type(CardType.ATTACK)
            .cost(1)
            .build();

    private static final int FLUX_SIC = 2;
    private static final int FLUX_UPGRADE = 1;
    private static final int TEMP_FERAL_GAIN = 4;
    private static final int FERAL_UPGRADE = 1;

    public NaturesAnarchy() {
        super(data);

        baseMagicNumber = magicNumber = TEMP_FERAL_GAIN;
        __baseMagicThird = __magicThird = FLUX_SIC;

    }

    @Override
    protected void UpgradeParameters() {
        upgradeMagicNumber(FERAL_UPGRADE);
        upgradeMagicThird(FLUX_UPGRADE);
    }

    @Override
    public void useAlways(AbstractPlayer p, AbstractMonster m) {
        new ApplyPowerBuilder()
                .power(WolfPower.class)
                .amount(magicNumber)
                .addToBottom();

        new ApplyPowerBuilder()
                .power(LoseWolfPower.class)
                .amount(magicNumber)
                .addToBottom();
    }

    @Override
    public void useOnFlux(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < __magicThird; ++i) {
            addToBottom(
                    new SicWolvesAction()
            );
        }
    }
}
