package dkSTS.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.unique.ExhumeAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

public class ExhaustCardFromDiscardPileAction extends AbstractGameAction {

    private final boolean isRandom;
    private final boolean isFast;
    public ExhaustCardFromDiscardPileAction(final int amount) {
        this(amount, false, false);
    }

    public ExhaustCardFromDiscardPileAction(final int amount, final boolean isRandom, final boolean isFast) {
        this.isRandom = isRandom;
        this.isFast = isFast;
        this.setValues(AbstractDungeon.player, AbstractDungeon.player, amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;

    }

    @Override
    public void update() {
        AbstractPlayer p = (AbstractPlayer)source;
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (p.discardPile.group.size() == 0) {
                this.isDone = true;
            } else if (p.discardPile.group.size() <= amount) {
                ArrayList<AbstractCard> cardsToRemove = new ArrayList<>(p.discardPile.group);
                for (AbstractCard c : cardsToRemove) {
                    this.addToTop(
                            new ExhaustSpecificCardAction(c, p.discardPile, isFast)
                    );
                }
                this.isDone = true;
            } else {
                if (isRandom) {
                    for (int i = 0; i < amount; ++i ) {
                        this.addToTop(
                                new ExhaustSpecificCardAction(p.discardPile.getRandomCard(true), p.discardPile, isFast)
                        );
                        this.isDone = true;
                    }
                } else {
                    String exhaustMessage = "Choose " + amount + " cards to Exhaust.";
                    AbstractDungeon.gridSelectScreen.open(p.discardPile, amount, exhaustMessage, false);
                }
            }
            tickDuration();
        } else {
            if (AbstractDungeon.gridSelectScreen.selectedCards.size() == amount) {
                for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
                    c.unhover();
                    this.addToTop(
                            new ExhaustSpecificCardAction(c, p.discardPile, isFast)
                    );
                }
                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                this.isDone = true;
            }
            tickDuration();
        }


    }
}
