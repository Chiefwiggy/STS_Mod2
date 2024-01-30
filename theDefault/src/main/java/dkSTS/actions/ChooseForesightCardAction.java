package dkSTS.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import dkSTS.powers.NextTurnPlayCardPower;

public class ChooseForesightCardAction extends AbstractGameAction {
    public static final String TEXT = "Choose a card to use next turn.";
    private AbstractPlayer p;

    public ChooseForesightCardAction(int times) {
        this.p = AbstractDungeon.player;
        this.setValues(p,p, times);
        this.duration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (p.hand.isEmpty()) {
                this.isDone = true;
            } else {
                AbstractDungeon.handCardSelectScreen.open(TEXT, 1, false);
                p.hand.applyPowers();
                this.tickDuration();
                return;
            }
        }
        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            AbstractCard c = AbstractDungeon.handCardSelectScreen.selectedCards.group.get(0);

            this.addToTop(
                    new ApplyPowerAction(p, p, new NextTurnPlayCardPower(p, p, amount, c.makeCopy()))
            );

            this.addToTop(
                    new ExhaustSpecificCardAction(c, AbstractDungeon.handCardSelectScreen.selectedCards)
            );


            this.isDone = true;
        }
    }
}
