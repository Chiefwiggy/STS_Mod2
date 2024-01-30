package dkSTS.events.Helpers.Nodes;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class ChoiceBuilder {

    private final EventChoice choice;

    public ChoiceBuilder() {
        choice = new EventChoice();
    }

    public ChoiceBuilder next(EventNode node) {
        choice.node = node;
        return this;
    }

    public ChoiceBuilder text(String text) {
        choice.text = (p) -> text;
        return this;
    }

    public ChoiceBuilder text(DynamicTextFunction fn) {
        choice.text = fn;
        return this;
    }

    public ChoiceBuilder disabled_text(String text) {
        choice.disabled_text = (p) -> text;
        return this;
    }

    public ChoiceBuilder disabled_text(DynamicTextFunction text) {
        choice.disabled_text = text;
        return this;
    }

    public ChoiceBuilder onChoose(OnChooseFunction fn) {
        choice.onChoose = fn;
        return this;
    }

    public ChoiceBuilder preview(AbstractCard card) {
        choice.previewCard = card;
        choice.hasPreviewCard = true;
        return this;
    }

    public ChoiceBuilder preview(AbstractRelic relic) {
        choice.previewRelic = relic;
        choice.hasPreviewRelic = true;
        return this;
    }

    public ChoiceBuilder disableFunction(IsDisabledFunction fn) {
        choice.disabledFunction = fn;
        return this;
    }

    public EventChoice build() {
        return choice;
    }
}
