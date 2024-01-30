package dkSTS.events.Helpers;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.EventStrings;
import dkSTS.DefaultMod;

public class BruxaEventBuilder {

    private final BruxaEventData data;

    public BruxaEventBuilder() {
        data = new BruxaEventData();
    }

    public BruxaEventBuilder id(Class<?> clazz) {
        data.ID = DefaultMod.makeID(clazz.getSimpleName());
        return this;
    }

    public BruxaEventBuilder doRemoveCards(AscensionEventVariable<Integer> amount) {
        data.cardsToRemove = amount;
        data.doRemoveCards = true;
        return this;
    }

    public BruxaEventBuilder doRemoveCards(int amount) {
        data.cardsToRemove = new AscensionEventVariable<>(amount, amount);
        data.doRemoveCards = true;
        return this;
    }



    public BruxaEventData build() {



        EventStrings eventStrings = CardCrawlGame.languagePack.getEventString(data.ID);

        data.NAME = eventStrings.NAME;
        data.DESCRIPTIONS = eventStrings.DESCRIPTIONS;
        data.OPTIONS = eventStrings.OPTIONS;



        return data;
    }
}
