package dkSTS.cards.Skills.Common;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BerserkPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.cards.Helpers.ApplyPowerBuilder;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;

public class AssassinsBlade extends AbstractBruxaCard {

    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(AssassinsBlade.class)
            .img("AssassinsBlade.png")
            .type(CardType.SKILL)
            .rarity(CardRarity.COMMON)
            .target(CardTarget.SELF)
            .cost(1)
            .build();

    private static final int STR_GAIN_CARD_DRAW = 1;

    public AssassinsBlade() {
        super(data);

        baseMagicNumber = magicNumber = STR_GAIN_CARD_DRAW;
        exhaust = exhaustOnUseOnce = true;
    }

    @Override
    protected void UpgradeParameters() {
        upgradeBaseCost(0);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

        new ApplyPowerBuilder()
                .power(StrengthPower.class)
                .amount(magicNumber)
                .addToBottom();

        addToBottom(
                new DrawCardAction(1)
        );
    }
}
