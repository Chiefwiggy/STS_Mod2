package dkSTS.events.Helpers.Nodes;

import basemod.Pair;
import dkSTS.DefaultMod;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static dkSTS.DefaultMod.makeEventPath;

public class EventNodeBuilder {

    private final EventNode node;

    private final ArrayList<EventChoice> choiceArray;

    private boolean useIndividualAdd;


    public EventNodeBuilder() {
        node = new EventNode();
        choiceArray = new ArrayList<>();
        useIndividualAdd = true;
    }

    public EventNodeBuilder body(String body) {
        node.BODY_TEXT = (p) -> body;
        return this;
    }

    public EventNodeBuilder body(DynamicTextFunction fn) {
        node.BODY_TEXT = fn;
        return this;
    }

    public EventNodeBuilder img(String img) {
        node.BODY_IMAGE = makeEventPath(img);
        return this;
    }

    public EventNodeBuilder img_sts(String img) {
        node.BODY_IMAGE = img;
        return this;
    }

    public EventNodeBuilder isExitNode() {
        node.isExitNode = true;
        return this;
    }

    public EventNodeBuilder isExitNode(String customExitMessage) {
        node.isExitNode = true;
        node.EXIT_STRING = customExitMessage;
        return this;
    }

    public EventNodeBuilder addChoice(EventChoice choice) {
        choiceArray.add(choice);
        return this;
    }

    public EventNodeBuilder addChoices(GenerateChoicesFunction fn) {
        node.CHOICES = fn;
        useIndividualAdd = false;
        return this;
    }



    public EventNode build() {
        if (useIndividualAdd) {
            node.CHOICES = (p, rng) -> choiceArray;
        }
        return node;
    }


}
