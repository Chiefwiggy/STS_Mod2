package dkSTS.events;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.unique.RitualDaggerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.colorless.RitualDagger;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.events.AbstractEvent;
import com.megacrit.cardcrawl.events.city.BackToBasics;
import com.megacrit.cardcrawl.events.city.DrugDealer;
import com.megacrit.cardcrawl.events.city.Nest;
import com.megacrit.cardcrawl.events.exordium.GoldenWing;
import com.megacrit.cardcrawl.events.exordium.GoopPuddle;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.CallingBell;
import com.megacrit.cardcrawl.relics.EmptyCage;
import com.megacrit.cardcrawl.relics.MarkOfTheBloom;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.vfx.cardManip.PurgeCardEffect;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import dkSTS.DefaultMod;
import dkSTS.cards.Helpers.DamageActionBuilder;
import dkSTS.events.Helpers.*;
import dkSTS.events.Helpers.Nodes.ChoiceBuilder;
import dkSTS.events.Helpers.Nodes.EventNode;
import dkSTS.events.Helpers.Nodes.EventNodeBuilder;
import dkSTS.events.Helpers.Nodes.OnChooseFunction;
import dkSTS.relics.*;

import java.util.ArrayList;

public class MysteriousCaller extends AbstractCustomEvent {
    private static final AscensionEventVariable<Integer> CARDS_TO_REMOVE = new AscensionEventVariable<>(2,2 );

    public static BruxaEventData data = new BruxaEventBuilder()
            .id(MysteriousCaller.class)
            .doRemoveCards(2)
            .build();

    public MysteriousCaller() {
        super(data, createSequencer());
    }

    public static EventSequencer createSequencer() {
        EventSequencer es = new EventSequencer();

        AscensionEventHPVariable BREAK_CONNECTION = new AscensionEventHPVariable();
        BREAK_CONNECTION.setBase(0.125f);
        BREAK_CONNECTION.setA15(0.18f);

        AscensionEventHPVariable SACRIFICE_HP = new AscensionEventHPVariable();
        SACRIFICE_HP.setBase(0.225f);
        SACRIFICE_HP.setA15(0.30f);

        AscensionEventVariable<Integer> SACRIFICE_GOLD = new AscensionEventVariable<>();
        SACRIFICE_GOLD.setBase(250);
        SACRIFICE_GOLD.setA15(350);

        AscensionEventHPVariable SMASH_MIRROR = new AscensionEventHPVariable();
        SMASH_MIRROR.setBase(0.075f);
        SMASH_MIRROR.setA15(0.12f);



        EventNode leaveRudely = new EventNodeBuilder()
                .body(data.DESCRIPTIONS[15])
                .img_sts("images/events/vampires.jpg")
                .isExitNode()
                .build();

        EventNode leaveSomewhatRudely = new EventNodeBuilder()
                .body(data.DESCRIPTIONS[12])
                .img_sts("images/events/vampires.jpg")
                .isExitNode()
                .build();

        EventNode leaveMidAttunement = new EventNodeBuilder()
                .body(data.DESCRIPTIONS[16])
                .img_sts("images/events/vampires.jpg")
                .isExitNode()
                .build();

        EventNode declinePower = new EventNodeBuilder()
                .body(data.DESCRIPTIONS[11])
                .img_sts("images/events/vampires.jpg")
                .isExitNode()
                .build();

        EventNode removeCards = new EventNodeBuilder()
                .body(data.DESCRIPTIONS[8])
                .img_sts("images/events/vampires.jpg")
                .isExitNode()
                .build();

        EventNode expungeCurses = new EventNodeBuilder()
                .body(data.DESCRIPTIONS[9])
                .img_sts("images/events/vampires.jpg")
                .isExitNode()
                .build();

        EventNode gainTreasure = new EventNodeBuilder()
                .body(data.DESCRIPTIONS[10])
                .img_sts("images/events/vampires.jpg")
                .isExitNode()
                .build();

        EventNode gainPower = new EventNodeBuilder()
                .body(data.DESCRIPTIONS[7])
                .img_sts("images/events/vampires.jpg")
                .addChoice(
                        new ChoiceBuilder()
                                .text((p) -> data.OPTIONS[18] + CARDS_TO_REMOVE.get() + data.OPTIONS[19])
                                .onChoose((p) -> {
                                    openDiscardMenu();
                                })
                                .next(removeCards)
                                .build()
                )
                .addChoice(
                        new ChoiceBuilder()
                                .text(data.OPTIONS[20])
                                .next(expungeCurses)
                                .onChoose((p) -> {
                                    AbstractDungeon.getCurrRoom().spawnRelicAndObtain(p.drawX, p.drawY, new MarkOfTheThorn());

                                    //remove ALL relics
                                    ArrayList<AbstractCard> cardsToRemove = new ArrayList<>();
                                    for (AbstractCard c : p.masterDeck.group) {
                                        if (c.type == AbstractCard.CardType.CURSE) {
                                            cardsToRemove.add(c);
                                        }
                                    }
                                    for (AbstractCard c : cardsToRemove) {
                                        AbstractDungeon.effectList.add(new PurgeCardEffect(c));
                                        p.masterDeck.removeCard(c);
                                    }
                                })
                                .disableFunction((p) -> {
                                    for (AbstractCard c : p.masterDeck.group) {
                                        if (c.type == AbstractCard.CardType.CURSE) return false;
                                    }
                                    return true;
                                })
                                .disabled_text(data.OPTIONS[25])
                                .build()
                )
                .addChoice(
                        new ChoiceBuilder()
                                .text(data.OPTIONS[21])
                                .next(gainTreasure)
                                .onChoose((p) -> {
                                    AbstractDungeon.getCurrRoom().spawnRelicAndObtain(p.drawX, p.drawY, new MarkOfEnervation());
                                    AbstractDungeon.combatRewardScreen.open();
                                    AbstractDungeon.combatRewardScreen.rewards.clear();
                                    AbstractDungeon.combatRewardScreen.rewards.add(new RewardItem(AbstractDungeon.returnRandomScreenlessRelic(AbstractRelic.RelicTier.COMMON)));
                                    AbstractDungeon.combatRewardScreen.rewards.add(new RewardItem(AbstractDungeon.returnRandomScreenlessRelic(AbstractRelic.RelicTier.COMMON)));
                                    AbstractDungeon.combatRewardScreen.rewards.add(new RewardItem(AbstractDungeon.returnRandomScreenlessRelic(AbstractRelic.RelicTier.UNCOMMON)));
                                    AbstractDungeon.combatRewardScreen.rewards.add(new RewardItem(AbstractDungeon.returnRandomScreenlessRelic(AbstractRelic.RelicTier.UNCOMMON)));
                                    AbstractDungeon.combatRewardScreen.rewards.add(new RewardItem(AbstractDungeon.returnRandomScreenlessRelic(AbstractRelic.RelicTier.RARE)));
                                    AbstractDungeon.combatRewardScreen.rewards.add(new RewardItem(AbstractDungeon.returnRandomScreenlessRelic(AbstractRelic.RelicTier.RARE)));
                                    AbstractDungeon.combatRewardScreen.positionRewards();
                                    AbstractDungeon.getCurrRoom().rewardPopOutTimer = 0.25F;
                                })
                                .build()
                )
                .addChoice(
                        new ChoiceBuilder()
                                .text(data.OPTIONS[22])
                                .next(declinePower)
                                .build()
                )
                .build();


        EventNode goldChoice = new EventNodeBuilder()
                .body(data.DESCRIPTIONS[4])
                .img_sts("images/events/vampires.jpg")
                .addChoice(
                        new ChoiceBuilder()
                                .text(data.OPTIONS[17])
                                .next(gainPower)
                                .build()
                )
                .build();

        EventNode pestleChoice = new EventNodeBuilder()
                .body(data.DESCRIPTIONS[5])
                .img_sts("images/events/vampires.jpg")
                .addChoice(
                        new ChoiceBuilder()
                                .text(data.OPTIONS[17])
                                .next(gainPower)
                                .build()
                )
                .build();

        EventNode hpChoice = new EventNodeBuilder()
                .body(data.DESCRIPTIONS[6])
                .img_sts("images/events/vampires.jpg")
                .addChoice(
                        new ChoiceBuilder()
                                .text(data.OPTIONS[17])
                                .next(gainPower)
                                .build()
                )
                .build();

        EventNode payDebtChoice = new EventNodeBuilder()
                .body(data.DESCRIPTIONS[3])
                .img_sts("images/events/vampires.jpg")
                .addChoice(
                        new ChoiceBuilder()
                                .text((p) -> data.OPTIONS[7] + SACRIFICE_GOLD.get() + data.OPTIONS[8])
                                .disabled_text((p) -> data.OPTIONS[9] + SACRIFICE_GOLD.get() + data.OPTIONS[10])
                                .disableFunction((p) -> p.gold < SACRIFICE_GOLD.get())
                                .onChoose((p) -> {
                                    p.loseGold(SACRIFICE_GOLD.get());
                                    p.getRelic(AncestorsDebt.data.ID).usedUp();
                                })
                                .next(goldChoice)
                                .build()
                )
                .addChoice(
                        new ChoiceBuilder()
                                .text(data.OPTIONS[11])
                                .disabled_text(data.OPTIONS[12])
                                .disableFunction((p) -> {
                                    return !p.hasRelic(CrystalCharm.data.ID);
                                })
                                .onChoose((p) -> {
                                    p.loseRelic(CrystalCharm.data.ID);
                                    p.getRelic(AncestorsDebt.data.ID).usedUp();
                                })
                                .next(pestleChoice)
                                .build()
                )
                .addChoice(
                        new ChoiceBuilder()
                                .text((p) -> data.OPTIONS[13] + SACRIFICE_HP.getHP(p) + data.OPTIONS[14])
                                .next(hpChoice)
                                .onChoose((p) -> {
                                    p.decreaseMaxHealth(SACRIFICE_HP.getHP(p));
                                    p.getRelic(AncestorsDebt.data.ID).usedUp();
                                })
                                .build()
                )
                .addChoice(
                        new ChoiceBuilder()
                                .text((p) -> data.OPTIONS[15] + BREAK_CONNECTION.getHP(p) + data.OPTIONS[16])
                                .onChoose((p) -> {
                                    new DamageActionBuilder()
                                            .target(p)
                                            .damage(BREAK_CONNECTION.getHP(p))
                                            .addToEvent();
                                    AbstractDungeon.effectList.add(new FlashAtkImgEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, AbstractGameAction.AttackEffect.FIRE));
                                })
                                .next(leaveSomewhatRudely)
                                .build()
                )
                .build();

        EventNode attune = new EventNodeBuilder()
                .body(data.DESCRIPTIONS[2])
                .img_sts("images/events/vampires.jpg")
                .addChoice(
                        new ChoiceBuilder()
                                .text(data.OPTIONS[17])
                                .next(payDebtChoice)
                                .build()
                )
                .build();

        EventNode attune_preview = new EventNodeBuilder()
                .body(data.DESCRIPTIONS[1])
                .img_sts("images/events/vampires.jpg")
                .addChoice(
                        new ChoiceBuilder()
                                .text(data.OPTIONS[4])
                                .next(attune)
                                .build()
                )
                .addChoice(
                        new ChoiceBuilder()
                                .text((p) -> data.OPTIONS[5] + BREAK_CONNECTION.getHP(p) + data.OPTIONS[6])
                                .next(leaveMidAttunement)
                                .onChoose((p) -> {
                                    new DamageActionBuilder()
                                            .target(p)
                                            .damage(BREAK_CONNECTION.getHP(p))
                                            .addToEvent();
                                    AbstractDungeon.effectList.add(new FlashAtkImgEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, AbstractGameAction.AttackEffect.FIRE));
                                })
                                .build()
                )
                .build();

        EventNode smashMirror = new EventNodeBuilder()
                .body(data.DESCRIPTIONS[13])
                .img_sts("images/events/vampires.jpg")
                .isExitNode()
                .build();

        EventNode leaveImmediately = new EventNodeBuilder()
                .body(data.DESCRIPTIONS[14])
                .img_sts("images/events/vampires.jpg")
                .addChoice(
                        new ChoiceBuilder()
                                .text(data.OPTIONS[23])
                                .next(attune)
                                .build()
                )
                .addChoice(
                        new ChoiceBuilder()
                                .text(data.OPTIONS[24])
                                .next(leaveRudely)
                                .build()
                )
                .build();

        EventNode start = new EventNodeBuilder()
                .body(data.DESCRIPTIONS[0])
                .img_sts("images/events/vampires.jpg")
                .addChoice(
                        new ChoiceBuilder()
                                .text(data.OPTIONS[0])
                                .next(attune_preview)
                                .build()
                )
                .addChoice(
                        new ChoiceBuilder()
                                .text((p) -> data.OPTIONS[1] + SMASH_MIRROR.getHP(p) + data.OPTIONS[2])
                                .next(smashMirror)
                                .onChoose((p) -> {
                                    new DamageActionBuilder()
                                            .target(p)
                                            .damage(SMASH_MIRROR.getHP(p))
                                            .addToEvent();
                                    AbstractDungeon.effectList.add(new FlashAtkImgEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, AbstractGameAction.AttackEffect.FIRE));
                                    AbstractDungeon.getCurrRoom().spawnRelicAndObtain(p.drawX, p.drawY, new MysteriousSapphire());
                                })
                                .build()
                )
                .addChoice(
                        new ChoiceBuilder()
                                .text(data.OPTIONS[3])
                                .next(leaveImmediately)
                                .build()
                )
                .build();



        es.setStartingNode(start);


        return es;
    }

    private static void removeImportantCards(AbstractPlayer p, int amountToRemove) {
        int cardsRemoved = 0;
        CardGroup importantCards = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        //first pass - grab powers, colorless cards and rare cards.
        for (AbstractCard card : p.masterDeck.group) {
            if (card.type == AbstractCard.CardType.POWER || card.rarity == AbstractCard.CardRarity.RARE || (card.color == AbstractCard.CardColor.COLORLESS && card.rarity == AbstractCard.CardRarity.UNCOMMON)) {
                importantCards.group.add(card);
            }
        }
        int cardsCanRemove = Math.min(amountToRemove - cardsRemoved, importantCards.size());
        DefaultMod.logger.warn("CARDS TO REMOVE 1: " + cardsCanRemove + " versus: " + importantCards.size());
        cardsRemoved += cardsCanRemove;
        removeCardsFromGroup(cardsCanRemove, importantCards);

        if (amountToRemove == cardsRemoved) {
            return;
        }
        importantCards.group.clear();

        //then grab uncommons if you don't have enough
        for (AbstractCard card : p.masterDeck.group) {
            if (card.rarity == AbstractCard.CardRarity.UNCOMMON) {
                importantCards.group.add(card);
            }
        }

        cardsCanRemove = Math.min(amountToRemove - cardsRemoved, importantCards.size());
        DefaultMod.logger.warn("CARDS TO REMOVE 2: " + cardsCanRemove + " versus: " + importantCards.size());
        cardsRemoved += cardsCanRemove;
        removeCardsFromGroup(cardsCanRemove, importantCards);


        if (amountToRemove == cardsRemoved) {
            return;
        }

        removeCardsFromGroup(amountToRemove - cardsRemoved, CardGroup.getGroupWithoutBottledCards(AbstractDungeon.player.masterDeck.getPurgeableCards()));


    };

    private static void removeCardsFromGroup(int amountToRemove, CardGroup group) {
        DefaultMod.logger.warn("GOT HERE DUDE " + amountToRemove + " out of " + group.size());
        int init_size = group.size();
        for (int i = 0; i < amountToRemove && i < init_size; ++i) {
            AbstractCard nc = group.getRandomCard(true);
            //remove card
            AbstractDungeon.effectList.add(new PurgeCardEffect(nc));
            AbstractDungeon.player.masterDeck.removeCard(nc);
            DefaultMod.logger.warn("Removed: " + nc.name);
            group.removeCard(nc);
        }
    }

    @Override
    protected void doAfterRemoveCards() {
        removeImportantCards(AbstractDungeon.player, CARDS_TO_REMOVE.get());
    }
}
