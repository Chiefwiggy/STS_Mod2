package dkSTS.cards.Skills.Common;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.red.BloodForBlood;
import com.megacrit.cardcrawl.cards.red.Rupture;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.cards.Helpers.ApplyPowerBuilder;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;
import dkSTS.powers.EvasionPower;

public class BurstOfMist extends AbstractBruxaCard {
    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(BurstOfMist.class)
            .img("BurstOfMist.png")
            .rarity(CardRarity.COMMON)
            .type(CardType.SKILL)
            .target(CardTarget.SELF)
            .cost(1)
            .build();

    private static final int BLOCK = 7;
    private static final int BLOCK_UPGRADE = 4;

    private static final int DISCARD = 1;

    public BurstOfMist() {
        super(data);

        baseBlock = block = BLOCK;
        baseMagicNumber = magicNumber = DISCARD;
    }

    @Override
    protected void UpgradeParameters() {
        upgradeBlock(BLOCK_UPGRADE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {

        this.addToBot(
                new GainBlockAction(p, block)
        );

        new ApplyPowerBuilder()
                .power(EvasionPower.class)
                .amount(magicNumber)
                .addToBottom();

        this.addToBot(
                new DiscardAction(p,p,magicNumber, false)
        );
    }
}
