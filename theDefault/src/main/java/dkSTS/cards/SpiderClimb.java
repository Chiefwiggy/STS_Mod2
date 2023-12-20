package dkSTS.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;
import dkSTS.powers.EvasionPower;
import dkSTS.powers.FrostPower;

public class SpiderClimb extends AbstractBruxaCard {

    private static final int EVASION = 3;

    private static final int COST = 2;
    private static final int UPGRADE_COST = 1;

    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(SpiderClimb.class)
            .img("Skill.png")
            .rarity(CardRarity.BASIC)
            .target(CardTarget.SELF)
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
        addToBottom(
                new ApplyPowerAction(p, p, new EvasionPower(p, p, magicNumber), magicNumber)
        );
    }
}
