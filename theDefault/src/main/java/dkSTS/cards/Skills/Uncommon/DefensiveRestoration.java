package dkSTS.cards.Skills.Uncommon;

import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.unique.ExhaustAllNonAttackAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;

public class DefensiveRestoration extends AbstractBruxaCard {
    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(DefensiveRestoration.class)
            .img("Skill.png")
            .type(CardType.SKILL)
            .target(CardTarget.SELF)
            .cost(2)
            .rarity(CardRarity.UNCOMMON)
            .build();

    private static final int EXHAUST_PER = 3;
    private static final int EXHAUST_PER_UPGRADE = 2;
    private static final int BASE_BLOCK = 8;

    private boolean doExhaust;

    public DefensiveRestoration() {
        super(data);

        baseMagicNumber = magicNumber = EXHAUST_PER;
        baseBlock = block = BASE_BLOCK;

        doExhaust = false;
    }


    @Override
    protected void UpgradeParameters() {
        UpgradeDescription();
        upgradeMagicNumber(EXHAUST_PER_UPGRADE);
        doExhaust = true;
    }

    @Override
    protected void applyPowersToBlock() {
        super.applyPowersToBlock();

        int statuses = 0;
        for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
            if (c.type == CardType.STATUS) {
                statuses++;
            }
        }
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c.type == CardType.STATUS) {
                statuses++;
            }
        }
        block += statuses * magicNumber;

        this.isBlockModified = this.block != this.baseBlock;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {


        this.addToBottom(
                new GainBlockAction(p, block)
        );

        if (doExhaust) {
            for (AbstractCard c : p.discardPile.group) {
                if (c.type == CardType.STATUS) {
                    this.addToTop(
                            new ExhaustSpecificCardAction(c, p.discardPile, true)
                    );
                }
            }
            for (AbstractCard c : p.hand.group) {
                if (c.type == CardType.STATUS) {
                    this.addToTop(
                            new ExhaustSpecificCardAction(c, p.hand, true)
                    );
                }
            }
        }
    }
}
