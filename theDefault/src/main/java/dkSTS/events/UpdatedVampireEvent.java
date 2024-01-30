package dkSTS.events;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.events.city.Vampires;
import com.megacrit.cardcrawl.relics.BloodVial;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import dkSTS.cards.Attacks.Starter.Bite;
import dkSTS.cards.HelperCards.AscendedBite;
import dkSTS.events.Helpers.AbstractCustomEvent;
import dkSTS.events.Helpers.BruxaEventBuilder;
import dkSTS.events.Helpers.BruxaEventData;
import dkSTS.events.Helpers.EventSequencer;
import dkSTS.events.Helpers.Nodes.ChoiceBuilder;
import dkSTS.events.Helpers.Nodes.EventNode;
import dkSTS.events.Helpers.Nodes.EventNodeBuilder;

import java.util.ArrayList;

public class UpdatedVampireEvent extends AbstractCustomEvent {

    public static BruxaEventData data = new BruxaEventBuilder()
            .id(UpdatedVampireEvent.class)
            .build();



    public UpdatedVampireEvent() {
        super(data, createSequencer());
    }


    private static EventSequencer createSequencer() {
        EventSequencer es = new EventSequencer();



        EventNode aid = new EventNodeBuilder()
                .body(data.DESCRIPTIONS[1])
                .img_sts("images/events/vampires.jpg")
                .isExitNode()
                .build();

        EventNode ascend = new EventNodeBuilder()
                .body(data.DESCRIPTIONS[2])
                .img_sts("images/events/vampires.jpg")
                .isExitNode()
                .build();

        EventNode leave = new EventNodeBuilder()
                .body(data.DESCRIPTIONS[3])
                .img_sts("images/events/vampires.jpg")
                .isExitNode()
                .build();

        EventNode start = new EventNodeBuilder()
                .img_sts("images/events/vampires.jpg")
                .body(data.DESCRIPTIONS[0])
                .addChoice(new ChoiceBuilder()
                        .text(data.OPTIONS[0])
                        .preview(new Bite())
                        .onChoose((p) -> {
                            CardCrawlGame.sound.play("EVENT_VAMP_BITE");
                            replaceAttacks(false, 4);
                        })
                        .next(aid)
                        .build()
                )
                .addChoice(new ChoiceBuilder()
                        .text(data.OPTIONS[1])
                        .preview(new AscendedBite())
                        .next(ascend)
                        .disableFunction((p) -> !p.hasRelic(BloodVial.ID))
                        .disabled_text(data.OPTIONS[3])
                        .onChoose((p) -> {
                            CardCrawlGame.sound.play("EVENT_VAMP_BITE");
                            replaceAttacks(true, 2);
                        })
                        .build()
                )
                .addChoice(new ChoiceBuilder()
                        .text(data.OPTIONS[2])
                        .next(leave)
                        .build()
                )
                .build();



        es.setStartingNode(start);



        return es;
    }

    private static void replaceAttacks(boolean isAscended, int amount) {
        AbstractPlayer p = AbstractDungeon.player;
        ArrayList<AbstractCard> cardsToRemove = new ArrayList<>();
        for (AbstractCard card : p.masterDeck.group) {
            if (card.tags.contains(AbstractCard.CardTags.STARTER_STRIKE)) {
                cardsToRemove.add(card);

            }
        }
        for (AbstractCard c : cardsToRemove) {
            p.masterDeck.removeCard(c);
        }


        for (int i = 0; i < amount; ++i) {
            AbstractCard c;
            if (isAscended) {
                c = new AscendedBite();
            } else {
                c = new Bite();
            }
            AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(c, (float) Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
        }

    }
}
