package dkSTS.events;

import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import dkSTS.events.Helpers.AbstractCustomEvent;
import dkSTS.events.Helpers.BruxaEventBuilder;
import dkSTS.events.Helpers.BruxaEventData;
import dkSTS.events.Helpers.EventSequencer;
import dkSTS.events.Helpers.Nodes.ChoiceBuilder;
import dkSTS.events.Helpers.Nodes.EventNode;
import dkSTS.events.Helpers.Nodes.EventNodeBuilder;
import dkSTS.events.Helpers.Nodes.OnChooseFunction;

public class TestEvent extends AbstractCustomEvent {

    public static BruxaEventData data = new BruxaEventBuilder()
            .id(TestEvent.class)
            .build();

    public static final String ID = data.ID;



    public TestEvent() {
        super(data, createSequencer());
    }

    public static EventSequencer createSequencer() {
        EventSequencer es = new EventSequencer();

        OnChooseFunction chooseWisely = (p) -> {
            p.increaseMaxHp(4, true);
        };

        OnChooseFunction choosePoorly = (p) -> {
            p.decreaseMaxHealth(4);
        };

        EventNode node2 = new EventNodeBuilder()
                .body("You made a good choice")
                .isExitNode()
                .build();


        EventNode node3 = new EventNodeBuilder()
                .body("YOU MADE A SHIT CHOICE")
                .isExitNode()
                .build();

        EventNode node1 = new EventNodeBuilder()
                .body("This is a gamer's code. What shall ye do?")
                .addChoice(new ChoiceBuilder()
                        .text("Choose me uwu")
                        .next(node2)
                        .onChoose(chooseWisely)
                        .build()
                )
                .addChoice(new ChoiceBuilder()
                        .text("Choose me muehehehe")
                        .next(node3)
                        .onChoose(choosePoorly)
                        .build()
                )
                .build();

        EventNode node0 = new EventNodeBuilder()
                .body("How do you do it.")
                .addChoice(new ChoiceBuilder()
                        .text("[Enter]")
                        .next(node1)
                        .disableFunction((p) -> p.gold > 50)
                        .disabled_text("Cannot enter this way")
                        .build()
                )
                .addChoice(new ChoiceBuilder()
                        .text("[Enter even Cooler]")
                        .preview(new Burn())
                        .next(node1)
                        .build()
                )
                .build();


        es.setStartingNode(node0);

        return es;
    }

}
