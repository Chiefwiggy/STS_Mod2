package dkSTS.cards.Skills.Common;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.cards.Helpers.ApplyPowerBuilder;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;

public class SaltAndSilver extends AbstractBruxaCard {
    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(SaltAndSilver.class)
            .img("SaltAndSilver.png")
            .type(CardType.SKILL)
            .rarity(CardRarity.COMMON)
            .target(CardTarget.SELF)
            .cost(0)
            .build();

    private static final int VULN = 1;
    private static final int ENERGY = 2;

    public SaltAndSilver() {
        super(data);

        baseMagicNumber = magicNumber = VULN;
        __baseMagicThird = __magicThird = ENERGY;

        exhaust = true;
    }

    @Override
    protected void UpgradeParameters() {
        UpgradeDescription();
        selfRetain = true;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        new ApplyPowerBuilder()
                .power(VulnerablePower.class)
                .amount(magicNumber)
                .addToBottom();

        this.addToBottom(
                new GainEnergyAction(__magicThird)
        );

    }
}
