package dkSTS.cards.Skills.Uncommon;

import com.megacrit.cardcrawl.actions.unique.GainBlockRandomMonsterAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.events.city.Vampires;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import com.megacrit.cardcrawl.powers.EnergizedPower;
import com.megacrit.cardcrawl.powers.NextTurnBlockPower;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.cards.Helpers.ApplyPowerBuilder;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;

public class GlyphOfVersatility extends AbstractBruxaCard {
    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(GlyphOfVersatility.class)
            .img("GlyphOfVersatility.png")
            .rarity(CardRarity.UNCOMMON)
            .type(CardType.SKILL)
            .cost(1)
            .target(CardTarget.SELF)
            .build();

    private static final int BLOCK = 5;
    private static final int BLOCK_UPGRADE = 4;
    private static final int CARDS = 1;
    private static final int CARDS_UPGRADE = 1;

    private static final int ENERGY = 1;
    private static final int ENERGY_UPGRADE = 1;


    public GlyphOfVersatility() {
        super(data);

        baseBlock = block = BLOCK;
        baseMagicNumber = magicNumber = CARDS;
        __baseMagicThird = __magicThird = ENERGY;

        exhaust = exhaustOnUseOnce = true;
        selfRetain = true;
    }

    @Override
    protected void UpgradeParameters() {
        upgradeBlock(BLOCK_UPGRADE);
        upgradeMagicNumber(CARDS_UPGRADE);
        upgradeMagicThird(ENERGY_UPGRADE);
        UpgradeDescription();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        new ApplyPowerBuilder()
                .power(EnergizedPower.class)
                .amount(__magicThird)
                .addToBottom();

        new ApplyPowerBuilder()
                .power(NextTurnBlockPower.class)
                .amount(block)
                .addToBottom();

        new ApplyPowerBuilder()
                .power(DrawCardNextTurnPower.class)
                .amount(magicNumber)
                .addToBottom();
    }
}
