package dkSTS.events.Helpers;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.GenericEventDialog;
import dkSTS.DefaultMod;
import dkSTS.events.Helpers.Nodes.EventNode;

import java.util.LinkedList;
import java.util.Queue;

public class EventSequencer {

    private EventNode headNode;
    private EventNode currentNode;
    GenericEventDialog dialog;

    private int previousOptions = 0;


    public EventSequencer() {
        this.dialog = null;
    }

    public void setStartingNode(EventNode node) {
        headNode = node;
        currentNode = headNode;
    }

    public void setDialog(GenericEventDialog e) {
        this.dialog = e;
    }

    public void Start() {
        headNode.Load(dialog);
    }

    public void Choose(int choice) {
        currentNode = currentNode.Choose(choice);
        DefaultMod.logger.warn(currentNode);
        currentNode.Load(dialog);

    }

    public String GetStartingText() {
        return headNode.BODY_TEXT.createText(AbstractDungeon.player);
    }

    public String GetStartingImage() {
        return headNode.BODY_IMAGE;
    }

    public boolean IsCurrentExitNode() {
        return currentNode.isExitNode;
    }



}
