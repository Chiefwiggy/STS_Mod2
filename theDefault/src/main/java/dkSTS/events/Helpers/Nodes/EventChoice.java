package dkSTS.events.Helpers.Nodes;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import dkSTS.DefaultMod;

public class EventChoice {
    public EventNode node;
    public DynamicTextFunction text;
    public DynamicTextFunction disabled_text;
    public OnChooseFunction onChoose;
    public IsDisabledFunction disabledFunction;
    public AbstractCard previewCard;
    public AbstractRelic previewRelic;

    public boolean hasPreviewCard;
    public boolean hasPreviewRelic;

    public EventChoice() {
        hasPreviewCard = false;
        previewCard = null;
        previewRelic = null;
        onChoose = (p) -> {};
        disabledFunction = (p) -> { return false; };
        text = (p) -> "";
    }

    public boolean getDisabled() {
        return disabledFunction.isDisabled(AbstractDungeon.player);
    }

}
