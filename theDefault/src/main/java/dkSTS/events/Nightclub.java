package dkSTS.events;

import basemod.Pair;
import basemod.devcommands.relic.RelicPool;
import basemod.helpers.RelicType;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.curses.Clumsy;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.shrines.WeMeetAgain;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.Circlet;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;
import dkSTS.characters.TheBruxa;
import dkSTS.events.Helpers.*;
import dkSTS.events.Helpers.Nodes.ChoiceBuilder;
import dkSTS.events.Helpers.Nodes.EventChoice;
import dkSTS.events.Helpers.Nodes.EventNode;
import dkSTS.events.Helpers.Nodes.EventNodeBuilder;

import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class Nightclub extends AbstractCustomEvent {
    public static BruxaEventData data = new BruxaEventBuilder()
            .id(Nightclub.class)
            .build();

    private static final AscensionEventVariable<Integer> commonPrice = new AscensionEventVariable<>(130, 150);
    private static final AscensionEventVariable<Integer> uncommonPrice = new AscensionEventVariable<>(225, 250);
    private static final AscensionEventVariable<Integer> rarePrice = new AscensionEventVariable<>(270, 300);
    private static final AscensionEventHPVariable MAX_HP_GAIN = new AscensionEventHPVariable(0.05f, 0.08f);


    public Nightclub() {
        super(data, createSequencer());
    }

    private static EventSequencer createSequencer() {

        EventSequencer es = new EventSequencer();

        EventNode initialRefuse = new EventNodeBuilder()
                .body(data.DESCRIPTIONS[6])
                .img_sts("images/events/vampires.jpg")
                .isExitNode()
                .build();

        EventNode dancedRefuseTrade = new EventNodeBuilder()
                .body(data.DESCRIPTIONS[8])
                .img_sts("images/events/vampires.jpg")
                .isExitNode()
                .build();

        EventNode successTrade = new EventNodeBuilder()
                .body(data.DESCRIPTIONS[7])
                .img_sts("images/events/vampires.jpg")
                .isExitNode()
                .build();

        EventNode makeDeal = new EventNodeBuilder()
                .body(data.DESCRIPTIONS[5])
                .img_sts("images/events/vampires.jpg")
                .addChoices((p, rng) -> createEventChoiceList(p, rng, successTrade, dancedRefuseTrade))
                .build();

        EventNode start = new EventNodeBuilder()
                .body((p) -> {
                    if (p.chosenClass.equals(TheBruxa.Enums.THE_BRUXA)) {
                        return data.DESCRIPTIONS[0] + data.DESCRIPTIONS[1];
                    } else {
                        switch (p.chosenClass) {
                            case DEFECT:
                                return data.DESCRIPTIONS[0] + data.DESCRIPTIONS[4];
                            case WATCHER:
                            case THE_SILENT:
                                return data.DESCRIPTIONS[0] + data.DESCRIPTIONS[3];
                            case IRONCLAD:
                                return data.DESCRIPTIONS[0] + data.DESCRIPTIONS[2];
                            default:
                                return "";
                        }
                    }
                })
                .img_sts("images/events/vampires.jpg")
                .addChoice(
                        new ChoiceBuilder()
                                .text(data.OPTIONS[0])
                                .disableFunction((p) -> p.masterDeck.findCardById(Clumsy.ID) != null)
                                .disabled_text(data.OPTIONS[1])
                                .onChoose((p) -> {
                                    //remove random card
                                    AbstractCard c = p.masterDeck.getRandomCard(AbstractDungeon.cardRng);
                                    AbstractDungeon.effectList.add(new PurgeCardEffect(c));
                                    p.masterDeck.removeCard(c);
                                })
                                .next(makeDeal)
                                .build()
                )
                .addChoice(
                        new ChoiceBuilder()
                                .text(data.OPTIONS[2])
                                .next(initialRefuse)
                                .build()
                )
                .build();


        es.setStartingNode(start);

        return es;
    }

    private static ArrayList<EventChoice> createEventChoiceList(AbstractPlayer pp, Random eventRng, EventNode next, EventNode exit) {
        ArrayList<EventChoice> array = new ArrayList<>();
        ArrayList<Pair<EventChoice, Float>> rngList = new ArrayList<>();


        ArrayList<AbstractRelic> commonRelics = new ArrayList<>();
        ArrayList<AbstractRelic> uncommonRelics = new ArrayList<>();
        ArrayList<AbstractRelic> rareRelics = new ArrayList<>();

        for (AbstractRelic r : pp.relics) {
            switch(r.tier) {
                case COMMON:
                    commonRelics.add(r);
                    break;
                case UNCOMMON:
                    uncommonRelics.add(r);
                    break;
                case RARE:
                    rareRelics.add(r);
                    break;
                default:
                    break;
            }
        }

        AbstractRelic commonRelicToLose = new Circlet();
        if (commonRelics.size() > 0) {
            commonRelicToLose = commonRelics.get(eventRng.random(0, commonRelics.size()-1));
        }
        AbstractRelic uncommonRelicToLose = new Circlet();
        if (uncommonRelics.size() > 0) {
            uncommonRelicToLose = uncommonRelics.get(eventRng.random(0, uncommonRelics.size()-1));
        }

        AbstractRelic rareRelicToLose = new Circlet();
        if (rareRelics.size() > 0) {
            rareRelicToLose = rareRelics.get(eventRng.random(0, rareRelics.size()-1));
        }


        AbstractRelic gCommonRelic = AbstractDungeon.returnRandomRelic(AbstractRelic.RelicTier.COMMON);
        AbstractRelic gUncommonRelic = AbstractDungeon.returnRandomRelic(AbstractRelic.RelicTier.UNCOMMON);
        AbstractRelic gRareRelic = AbstractDungeon.returnRandomRelic(AbstractRelic.RelicTier.RARE);

        AbstractRelic finalCommonRelicToLose = commonRelicToLose;
        AbstractRelic finalUncommonRelicToLose = uncommonRelicToLose;
        AbstractRelic finalRareRelicToLose = rareRelicToLose;
        EventChoice commonChoice =
                new ChoiceBuilder()
                        .text(data.OPTIONS[3] + commonRelicToLose.name + data.OPTIONS[4] + gCommonRelic.name + data.OPTIONS[5])
                        .onChoose((p) -> {
                            p.loseRelic(finalCommonRelicToLose.relicId);
                            AbstractDungeon.getCurrRoom().spawnRelicAndObtain(p.drawX, p.drawY, gCommonRelic);
                        })
                        .disableFunction((p) -> commonRelics.size() == 0)
                        .disabled_text(data.OPTIONS[11])
                        .preview(gCommonRelic)
                        .next(next)
                        .build();

        array.add(commonChoice);

        EventChoice uncommonChoice =
                new ChoiceBuilder()
                        .text(data.OPTIONS[3] + uncommonRelicToLose.name + data.OPTIONS[4] + gUncommonRelic.name + data.OPTIONS[5])
                        .onChoose((p) -> {
                            p.loseRelic(finalUncommonRelicToLose.relicId);
                            AbstractDungeon.getCurrRoom().spawnRelicAndObtain(p.drawX, p.drawY, gUncommonRelic);
                        })
                        .disableFunction((p) -> uncommonRelics.size() == 0)
                        .disabled_text(data.OPTIONS[12])
                        .preview(gUncommonRelic)
                        .next(next)
                        .build();

        array.add(uncommonChoice);

        EventChoice rareChoice =
                new ChoiceBuilder()
                        .text(data.OPTIONS[3] + rareRelicToLose.name + data.OPTIONS[4] + gRareRelic.name + data.OPTIONS[5])
                        .onChoose((p) -> {
                            p.loseRelic(finalRareRelicToLose.relicId);
                            AbstractDungeon.getCurrRoom().spawnRelicAndObtain(p.drawX, p.drawY, gRareRelic);
                        })
                        .disableFunction((p) -> rareRelics.size() == 0)
                        .disabled_text(data.OPTIONS[13])
                        .preview(gRareRelic)
                        .next(next)
                        .build();

        array.add(rareChoice);

        // cost 135
        EventChoice buyCommon =
                new ChoiceBuilder()
                        .text(data.OPTIONS[6] + commonPrice.get() + data.OPTIONS[7] + gCommonRelic.name + data.OPTIONS[8])
                        .onChoose((p) -> {
                            p.loseGold(commonPrice.get());
                            AbstractDungeon.getCurrRoom().spawnRelicAndObtain(p.drawX, p.drawY, gCommonRelic);
                        })
                        .disableFunction((p) -> p.gold < commonPrice.get())
                        .disabled_text(data.OPTIONS[14] + commonPrice.get() + data.OPTIONS[15])
                        .preview(gCommonRelic)
                        .next(next)
                        .build();

        //cost 225
        EventChoice buyUncommon =
                new ChoiceBuilder()
                        .text(data.OPTIONS[6] + uncommonPrice.get() + data.OPTIONS[7] + gUncommonRelic.name + data.OPTIONS[8])
                        .onChoose((p) -> {
                            p.loseGold(uncommonPrice.get());
                            AbstractDungeon.getCurrRoom().spawnRelicAndObtain(p.drawX, p.drawY, gUncommonRelic);
                        })
                        .disableFunction((p) -> p.gold < uncommonPrice.get())
                        .disabled_text(data.OPTIONS[14] + uncommonPrice.get() + data.OPTIONS[15])
                        .preview(gUncommonRelic)
                        .next(next)
                        .build();

        //cost 270
        EventChoice buyRare =
                new ChoiceBuilder()
                        .text(data.OPTIONS[6] + rarePrice.get() + data.OPTIONS[7] + gRareRelic.name + data.OPTIONS[8])
                        .onChoose((p) -> {
                            p.loseGold(rarePrice.get());
                            AbstractDungeon.getCurrRoom().spawnRelicAndObtain(p.drawX, p.drawY, gRareRelic);
                        })
                        .disableFunction((p) -> p.gold < rarePrice.get())
                        .disabled_text(data.OPTIONS[14] + rarePrice.get() + data.OPTIONS[15])
                        .preview(gRareRelic)
                        .next(next)
                        .build();

        rngList.add(new Pair<>(buyCommon, eventRng.random()));
        rngList.add(new Pair<>(buyUncommon, eventRng.random()));
        rngList.add(new Pair<>(buyRare, eventRng.random()));

        rngList.sort((a,b) -> {
            if (Objects.equals(a.getValue(), b.getValue())) {
                return 0;
            } else {
                return (b.getValue() - a.getValue() > 0) ? 1 : -1;
            }
        });


        array.add(rngList.get(0).getKey());

        EventChoice leaveChoice =
                new ChoiceBuilder()
                        .text((p) -> data.OPTIONS[9] + MAX_HP_GAIN.getHP(p) + data.OPTIONS[10] )
                        .next(exit)
                        .onChoose((p) -> {
                            p.increaseMaxHp(MAX_HP_GAIN.getHP(p), true);
                        })
                        .build();

        array.add(leaveChoice);
        return array;
    }
}
