package dkSTS.events.Helpers.Nodes;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.GenericEventDialog;
import dkSTS.DefaultMod;

import java.util.ArrayList;

import static dkSTS.DefaultMod.makeEventPath;

public class EventNode {
    public static final String G_IMG = makeEventPath("IdentityCrisisEvent.png");
    public DynamicTextFunction BODY_TEXT = (p) -> "[FORGOT TO PUT SOMETHING HERE]";
    public String BODY_IMAGE = G_IMG;

    public boolean isExitNode;
    public String EXIT_STRING = "[Leave]";


    public GenerateChoicesFunction CHOICES;

    private ArrayList<EventChoice> CHOICE_ARRAY;

    public EventNode() {
        isExitNode = false;
        CHOICE_ARRAY = null;
    }

    public void Load(final GenericEventDialog dialog) {
        dialog.loadImage(BODY_IMAGE);
        dialog.updateBodyText(BODY_TEXT.createText(AbstractDungeon.player));

        dialog.clearRemainingOptions();

        CHOICE_ARRAY = CHOICES.createChoices(AbstractDungeon.player, AbstractDungeon.eventRng);

        if (isExitNode) {
            dialog.updateDialogOption(0, EXIT_STRING);
        } else {
            boolean isFirst = true;
            for (EventChoice choice : CHOICE_ARRAY) {
                if (isFirst) {
                    if (choice.getDisabled()) {
                        dialog.updateDialogOption(0, choice.disabled_text.createText(AbstractDungeon.player), true);
                    } else {
                        dialog.updateDialogOption(0, choice.text.createText(AbstractDungeon.player), choice.previewCard);
                    }
                    isFirst = false;
                } else {
                    if (choice.getDisabled()) {
                        dialog.setDialogOption(choice.disabled_text.createText(AbstractDungeon.player), true);
                    } else {
                        dialog.setDialogOption(choice.text.createText(AbstractDungeon.player), choice.previewCard);
                    }
                }

            }
        }


    }
    public EventNode Choose(int choice) {
        CHOICE_ARRAY.get(choice).onChoose.onChoose(AbstractDungeon.player);
        return CHOICE_ARRAY.get(choice).node;
    }

}
