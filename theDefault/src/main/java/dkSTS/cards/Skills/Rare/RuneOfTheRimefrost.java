package dkSTS.cards.Skills.Rare;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BerserkPower;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.cards.Helpers.ApplyPowerBuilder;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;
import dkSTS.powers.RimefrostRunePower;

public class RuneOfTheRimefrost extends AbstractBruxaCard {
    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(RuneOfTheRimefrost.class)
            .img("Skill.png")
            .cost(1)
            .type(AbstractCard.CardType.SKILL)
            .rarity(AbstractCard.CardRarity.RARE)
            .target(AbstractCard.CardTarget.ENEMY)
            .build();

    private static final int FROZEN = 1;
    private static final int UPGRADE_COST = 0;

    public RuneOfTheRimefrost() {
        super(data);

        exhaust = exhaustOnUseOnce = true;
    }

    @Override
    protected void UpgradeParameters() {
        upgradeBaseCost(UPGRADE_COST);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        new ApplyPowerBuilder()
                .power(RimefrostRunePower.class)
                .amount(magicNumber)
                .target(m)
                .addToBottom();
    }
}
