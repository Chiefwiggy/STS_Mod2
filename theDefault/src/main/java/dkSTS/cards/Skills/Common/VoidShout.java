package dkSTS.cards.Skills.Common;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.cards.Abstracts.AbstractVoidfluxCard;
import dkSTS.cards.Helpers.ApplyPowerBuilder;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;
import dkSTS.powers.VitalityPower;

public class VoidShout extends AbstractVoidfluxCard {
    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(VoidShout.class)
            .img("VoidShout.png")
            .rarity(CardRarity.COMMON)
            .type(CardType.SKILL)
            .cost(2)
            .target(CardTarget.SELF)
            .build();

    private static final int BLOCK = 12;
    private static final int BLOCK_UPGRADE = 4;
    private static final int VITALITY = 1;
    private static final int VITALITY_UPGRADE = 1;

    public VoidShout() {
        super(data);
        baseBlock = BLOCK;
        baseMagicNumber = magicNumber = VITALITY;
    }

    @Override
    protected void UpgradeParameters() {
        upgradeBlock(BLOCK_UPGRADE);
        upgradeMagicNumber(VITALITY_UPGRADE);
    }

    @Override
    public void useAlways(AbstractPlayer p, AbstractMonster m) {
        this.addToBottom(
                new GainBlockAction(p, block)
        );
    }

    @Override
    public void useOnFlux(AbstractPlayer p, AbstractMonster m) {
        new ApplyPowerBuilder()
                .power(VitalityPower.class)
                .amount(magicNumber)
                .addToBottom();
    }

    @Override
    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        if (isPlayerBloodied()) {
            this.glowColor = Color.RED.cpy();
        }
    }


}
