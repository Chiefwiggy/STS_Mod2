package dkSTS.cards.Skills.Common;

import com.megacrit.cardcrawl.cards.green.DodgeAndRoll;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.NextTurnBlockPower;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.cards.Helpers.ApplyPowerBuilder;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;

public class GlyphOfShielding extends AbstractBruxaCard {
    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(GlyphOfShielding.class)
            .img("GlyphOfShielding.png")
            .rarity(CardRarity.COMMON)
            .target(CardTarget.SELF)
            .type(CardType.SKILL)
            .build();

    private static final int BLOCK = 9;
    private static final int BLOCK_UPGRADE = 4;


    public GlyphOfShielding() {
        super(data);

        baseBlock = block = BLOCK;
        selfRetain = true;
    }

    @Override
    protected void UpgradeParameters() {
        upgradeBlock(BLOCK_UPGRADE);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        new ApplyPowerBuilder()
                .power(NextTurnBlockPower.class)
                .amount(block)
                .addToBottom();

    }
}
