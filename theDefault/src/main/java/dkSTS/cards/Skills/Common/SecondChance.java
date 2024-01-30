package dkSTS.cards.Skills.Common;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dkSTS.actions.RedrawCardAction;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;

public class SecondChance extends AbstractBruxaCard {
    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(SecondChance.class)
            .img("SecondChance.png")
            .rarity(CardRarity.COMMON)
            .type(CardType.SKILL)
            .cost(0)
            .target(CardTarget.NONE)
            .build();

    private static final int REDRAW = 2;
    private static final int REDRAW_UPGRADE = 1;
    public SecondChance() {
        super(data);

        baseMagicNumber = magicNumber = REDRAW;
        cardsToPreview = new Dazed();
    }

    @Override
    protected void UpgradeParameters() {
        upgradeMagicNumber(REDRAW_UPGRADE);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        this.addToBottom(
                new RedrawCardAction(abstractPlayer, magicNumber)
        );
        this.addToBottom(
                new MakeTempCardInDiscardAction(new Dazed(), 1)
        );

    }
}
