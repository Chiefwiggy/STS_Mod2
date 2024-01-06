package dkSTS.cards.Skills.Common;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BerserkPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.cards.Helpers.ApplyPowerBuilder;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;

public class CloudOfDarkness extends AbstractBruxaCard {

    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(CloudOfDarkness.class)
            .img("CloudOfDarkness.png")
            .rarity(CardRarity.COMMON)
            .target(CardTarget.SELF)
            .type(CardType.SKILL)
            .cost(2)
            .build();

    private static final int BLOCK = 11;

    private static final int VIGOR = 5;

    public CloudOfDarkness() {
        super(data);

        baseBlock = BLOCK;
        baseMagicNumber = magicNumber = VIGOR;
    }

    @Override
    protected void UpgradeParameters() {
        upgradeBaseCost(1);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBottom(
                new GainBlockAction(p, block)
        );

        new ApplyPowerBuilder()
                .power(VigorPower.class)
                .amount(magicNumber)
                .addToBottom();
    }
}
