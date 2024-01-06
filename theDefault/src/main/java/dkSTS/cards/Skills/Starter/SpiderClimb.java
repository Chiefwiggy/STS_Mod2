package dkSTS.cards.Skills.Starter;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.cards.Helpers.ApplyPowerBuilder;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;
import dkSTS.powers.EvasionPower;

public class SpiderClimb extends AbstractBruxaCard {

    private static final int EVASION = 3;

    private static final int COST = 2;
    private static final int UPGRADE_COST = 1;

    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(SpiderClimb.class)
            .img("SpiderClimb.png")
            .rarity(CardRarity.BASIC)
            .target(CardTarget.SELF)
            .type(CardType.SKILL)
            .cost(COST)
            .build();



    public SpiderClimb() {
        super(data);

        baseMagicNumber = magicNumber = EVASION;
    }

    @Override
    protected void UpgradeParameters() {
        upgradeBaseCost(UPGRADE_COST);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        new ApplyPowerBuilder()
                .power(EvasionPower.class)
                .amount(magicNumber)
                .addToBottom();
    }
}
