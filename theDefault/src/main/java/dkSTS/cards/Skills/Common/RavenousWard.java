package dkSTS.cards.Skills.Common;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.UpgradeSpecificCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dkSTS.actions.PlayRandomCardAction;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.cards.Helpers.ApplyPowerBuilder;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;
import dkSTS.powers.BloodPower;

public class RavenousWard extends AbstractBruxaCard {
    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(RavenousWard.class)
            .img("Skill.png")
            .target(CardTarget.SELF)
            .type(CardType.SKILL)
            .rarity(CardRarity.COMMON)
            .cost(1)
            .build();

    private static final int BLOCK = 7;
    private static final int BLOCK_UPGRADE = 3;
    private static final int HUNGER_GAIN = 2;
    private static final int HUNGER_UPGRADE = 1;

    public RavenousWard() {
        super(data);

        baseMagicNumber = magicNumber = HUNGER_GAIN;
        baseBlock = block = BLOCK;
    }

    @Override
    protected void UpgradeParameters() {
        upgradeMagicNumber(HUNGER_UPGRADE);
        upgradeBlock(BLOCK_UPGRADE);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

        this.addToBottom(
                new GainBlockAction(abstractPlayer, block)
        );

        new ApplyPowerBuilder()
                .power(BloodPower.class)
                .amount(magicNumber)
                .addToBottom();
    }
}
