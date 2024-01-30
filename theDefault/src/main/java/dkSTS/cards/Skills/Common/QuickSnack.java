package dkSTS.cards.Skills.Common;

import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;
import dkSTS.powers.BloodPower;

public class QuickSnack extends AbstractBruxaCard {
    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(QuickSnack.class)
            .img("QuickSnack.png")
            .rarity(CardRarity.COMMON)
            .target(CardTarget.SELF)
            .type(CardType.SKILL)
            .cost(1)
            .build();

    private static final int HEAL = 5;
    private static final int HEAL_UPGRADE = 2;

    public QuickSnack() {
        super(data);
        baseMagicNumber = magicNumber = HEAL;

        exhaust = exhaustOnUseOnce = true;
    }

    @Override
    protected void UpgradeParameters() {
        upgradeMagicNumber(HEAL_UPGRADE);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBottom(
                new AddTemporaryHPAction(abstractPlayer, abstractPlayer, magicNumber)
        );
        addToBottom(
                new RemoveSpecificPowerAction(abstractPlayer, abstractPlayer, BloodPower.data.POWER_ID)
        );
    }
}
