package dkSTS.cards.Skills.Common;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.status.VoidCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BlurPower;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.cards.Abstracts.AbstractVoidfluxCard;
import dkSTS.cards.Helpers.ApplyPowerBuilder;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;

public class ShadowCloak extends AbstractVoidfluxCard {
    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(ShadowCloak.class)
            .rarity(CardRarity.COMMON)
            .target(CardTarget.SELF)
            .type(CardType.SKILL)
            .cost(1)
            .img("Voidcloak.png")
            .build();

    private static final int BLOCK = 11;
    private static final int BLOCK_UPGRADE = 4;

    public ShadowCloak() {
        super(data);

        baseBlock = block = BLOCK;
    }

    @Override
    protected void UpgradeParameters() {
        upgradeBlock(BLOCK_UPGRADE);
    }

    @Override
    public void useAlways(AbstractPlayer p, AbstractMonster m) {
        this.addToBottom(
                new GainBlockAction(p, block)
        );
        this.addToBottom(
                new MakeTempCardInDiscardAction(new VoidCard(), 1)
        );
    }

    @Override
    public void useOnFlux(AbstractPlayer p, AbstractMonster m) {
        new ApplyPowerBuilder()
                .power(BlurPower.class)
                .amount(1)
                .addToBottom();
    }
}
