package dkSTS.events.Helpers;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.events.city.BackToBasics;
import com.megacrit.cardcrawl.relics.EmptyCage;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;
import dkSTS.DefaultMod;

import java.util.ArrayList;

public abstract class AbstractCustomEvent extends AbstractImageEvent {

    protected EventSequencer eventSequencer;

    private boolean doRemoveCards;
    private static AscensionEventVariable<Integer> cardsToRemove = null;

    public AbstractCustomEvent(BruxaEventData data, EventSequencer sequencer) {
        super(data.NAME, sequencer.GetStartingText(), sequencer.GetStartingImage());

        eventSequencer = sequencer;
        eventSequencer.setDialog(imageEventText);

        doRemoveCards = data.doRemoveCards;
        cardsToRemove = data.cardsToRemove;

        eventSequencer.Start();
    }

    @Override
    public void update() {
        super.update();
        if (doRemoveCards) {
            updateRemoveCardsScreen();
        }
    }

    private void updateRemoveCardsScreen() {
        if (AbstractDungeon.gridSelectScreen.selectedCards.size() == cardsToRemove.get()) {
            ArrayList<AbstractCard> cards = AbstractDungeon.gridSelectScreen.selectedCards;
            for (AbstractCard c : cards) {
                AbstractDungeon.effectList.add(new PurgeCardEffect(c));
                AbstractDungeon.player.masterDeck.removeCard(c);
            }
            doRemoveCards = false;
            doAfterRemoveCards();
        }
    }

    protected void doAfterRemoveCards() {}

    protected static void openDiscardMenu() {
        if (CardGroup.getGroupWithoutBottledCards(AbstractDungeon.player.masterDeck.getPurgeableCards()).size() > 0) {
            AbstractDungeon.gridSelectScreen.open(CardGroup.getGroupWithoutBottledCards(AbstractDungeon.player.masterDeck.getPurgeableCards()), cardsToRemove.get(), "Choose " + cardsToRemove.get() + " cards to remove.", false);
        }
    }

    @Override
    protected final void buttonEffect(int i) {
        if (eventSequencer.IsCurrentExitNode()) {
            openMap();
        } else {
            eventSequencer.Choose(i);
        }

    }
}
