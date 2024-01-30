package dkSTS.cards.Powers.Uncommon;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import dkSTS.cards.Abstracts.AbstractFluxCard;
import dkSTS.cards.Abstracts.AbstractFluxVoidFluxCard;
import dkSTS.cards.Helpers.ApplyPowerBuilder;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;
import dkSTS.powers.VitalityPower;

public class Balance extends AbstractFluxVoidFluxCard {
    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(Balance.class)
            .img("Balance.png")
            .target(CardTarget.SELF)
            .type(CardType.POWER)
            .rarity(CardRarity.UNCOMMON)
            .cost(2)
            .build();

    private static final int GAIN = 3;
    private static final int GAIN_UPGRADE = 1;
    private static final int LOSS = 1;

    private static final int VIT_GAIN = 2;
    private static final int VIT_GAIN_UPGRADE = 1;

    public Balance() {
        super(data);

        baseMagicNumber = magicNumber = GAIN;
        __baseMagicThird = __magicThird = VIT_GAIN;
        defaultBaseSecondMagicNumber = defaultSecondMagicNumber = LOSS;

        selfRetain = true;
    }

    @Override
    protected void UpgradeParameters() {
        upgradeMagicNumber(GAIN_UPGRADE);
        upgradeMagicThird(VIT_GAIN_UPGRADE);
    }

    @Override
    public void useAlways(AbstractPlayer p, AbstractMonster m) {
        new ApplyPowerBuilder()
                .power(VitalityPower.class)
                .amount(__magicThird)
                .addToBottom();
    }

    @Override
    public void useOnFlux(AbstractPlayer p, AbstractMonster m) {
        new ApplyPowerBuilder()
                .power(DexterityPower.class)
                .amount(magicNumber)
                .addToBottom();

        new ApplyPowerBuilder()
                .power(StrengthPower.class)
                .amount(-defaultSecondMagicNumber)
                .addToBottom();
    }

    @Override
    public void useOnVoidflux(AbstractPlayer p, AbstractMonster m) {
        new ApplyPowerBuilder()
                .power(StrengthPower.class)
                .amount(magicNumber)
                .addToBottom();

        new ApplyPowerBuilder()
                .power(DexterityPower.class)
                .amount(-defaultSecondMagicNumber)
                .addToBottom();

    }
}
