package dkSTS.cards.Skills.Uncommon;

import com.megacrit.cardcrawl.actions.common.HealAction;
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
            .img("Skill.png")
            .rarity(CardRarity.UNCOMMON)
            .target(CardTarget.SELF)
            .type(CardType.SKILL)
            .cost(1)
            .build();

    private static final int HEAL = 4;
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
                new HealAction(abstractPlayer, abstractPlayer, magicNumber)
        );
        addToBottom(
                new RemoveSpecificPowerAction(abstractPlayer, abstractPlayer, BloodPower.data.POWER_ID)
        );
    }
}
