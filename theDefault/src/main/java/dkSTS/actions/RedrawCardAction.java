package dkSTS.actions;


import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.SoulGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.PlayerTurnEffect;
import java.util.ArrayList;

import dkSTS.DefaultMod;
import dkSTS.powers.AbstractCustomPower;
import dkSTS.powers.RewindPower;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RedrawCardAction extends AbstractGameAction {
    private boolean shuffleCheck;
    private static final Logger logger = LogManager.getLogger(com.megacrit.cardcrawl.actions.common.DrawCardAction.class.getName());
    public static ArrayList<AbstractCard> drawnCards = new ArrayList();
    private boolean clearDrawHistory;

    private final boolean reduceToZero;
    private final AbstractGameAction followUpAction;

    public RedrawCardAction(AbstractCreature source, int amount, boolean reduceToZero, AbstractGameAction action) {
        this.shuffleCheck = false;
        this.clearDrawHistory = true;
        this.reduceToZero = reduceToZero;
        this.followUpAction = action;

        this.setValues(AbstractDungeon.player, source, amount);
        this.actionType = ActionType.DRAW;
        if (Settings.FAST_MODE) {
            this.duration = Settings.ACTION_DUR_XFAST;
        } else {
            this.duration = Settings.ACTION_DUR_FASTER;
        }

    }

    public RedrawCardAction(AbstractCreature source, int amount, boolean reduceToZero) {
        this(source, amount, reduceToZero, null);
    }

    public RedrawCardAction(AbstractCreature source, int amount) {
        this(source, amount, false);
    }

    public RedrawCardAction(int amount) {
        this((AbstractCreature)null, amount, false);
    }


    public void update() {
        AbstractPlayer p = AbstractDungeon.player;
        if (this.clearDrawHistory) {
            this.clearDrawHistory = false;
            drawnCards.clear();
        }

        if (AbstractDungeon.player.hasPower("No Draw")) {
            AbstractDungeon.player.getPower("No Draw").flash();
            this.endActionWithFollowUp();
        } else if (this.amount <= 0) {
            this.endActionWithFollowUp();
        } else {
            int discardSize = AbstractDungeon.player.discardPile.size();
            int validCards = 0;
            if (!SoulGroup.isActive()) {
                if (p.hasPower(RewindPower.data.POWER_ID)) {
                    for (AbstractCard cd : p.discardPile.group) {
                        if (cd.type != AbstractCard.CardType.STATUS && cd.type != AbstractCard.CardType.CURSE) {
                            validCards++;
                        }
                    }
                }
                if (discardSize == 0) {
                    this.endActionWithFollowUp();
                }
                else if (AbstractDungeon.player.hand.size() == 10) {
                    AbstractDungeon.player.createHandIsFullDialog();
                    this.endActionWithFollowUp();
                } else
                {
                    if (!this.shuffleCheck) {
                        int tmp;
                        if (this.amount + AbstractDungeon.player.hand.size() > 10) {
                            tmp = 10 - (this.amount + AbstractDungeon.player.hand.size());
                            this.amount += tmp;
                            AbstractDungeon.player.createHandIsFullDialog();
                        }

                        if (this.amount > discardSize) {
                            tmp = this.amount - discardSize;
                        }

                        this.shuffleCheck = true;
                    }

                    this.duration -= Gdx.graphics.getDeltaTime();
                    if (this.amount != 0 && this.duration < 0.0F) {
                        if (Settings.FAST_MODE) {
                            this.duration = Settings.ACTION_DUR_XFAST;
                        } else {
                            this.duration = Settings.ACTION_DUR_FASTER;
                        }

                        --this.amount;
                        if (p.hasPower(RewindPower.data.POWER_ID)) {
                            if (validCards > 0) {
                                draw();
                                AbstractDungeon.player.hand.refreshHandLayout();
                            } else {
                                logger.warn("Player attempted to draw from an empty drawpile mid-DrawAction?MASTER DECK: " + AbstractDungeon.player.masterDeck.getCardNames());
                                this.endActionWithFollowUp();
                            }
                        } else {
                            if (!AbstractDungeon.player.discardPile.isEmpty()) {
                                drawnCards.add(AbstractDungeon.player.discardPile.getTopCard());
                                draw();
                                AbstractDungeon.player.hand.refreshHandLayout();
                            } else {
                                logger.warn("Player attempted to draw from an empty drawpile mid-DrawAction?MASTER DECK: " + AbstractDungeon.player.masterDeck.getCardNames());
                                this.endActionWithFollowUp();
                            }
                        }


                        if (this.amount == 0) {
                            this.endActionWithFollowUp();
                        }
                    }

                }
            }
        }
    }

    private void draw() {
        AbstractPlayer p = AbstractDungeon.player;
        for(int i = 0; i < 1; ++i) {
            if (p.discardPile.isEmpty()) {
                logger.info("ERROR: How did this happen? No cards in draw pile?? Player.java");
            } else {
                CardGroup validPool;
                if (p.hasPower(RewindPower.data.POWER_ID)) {
                    validPool = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
                    for (AbstractCard c : p.discardPile.group) {
                        if (c.type != AbstractCard.CardType.CURSE && c.type != AbstractCard.CardType.STATUS) validPool.addToRandomSpot(c);
                    }
                } else {
                    validPool = p.discardPile;
                }
                AbstractCard c = validPool.getRandomCard(true);
                c.current_x = CardGroup.DISCARD_PILE_X;
                c.current_y = CardGroup.DISCARD_PILE_Y;
                c.setAngle(0.0F, true);
                c.lighten(false);
                c.drawScale = 0.12F;
                c.targetDrawScale = 0.75F;
                c.triggerWhenDrawn();
                if (reduceToZero && c.cost > 0) {
                    c.isCostModifiedForTurn = true;
                    c.costForTurn = 0;
                }
                p.hand.addToHand(c);
                p.discardPile.removeCard(c);

                for (AbstractPower pow : p.powers) {
                    try {
                        AbstractCustomPower cpow = (AbstractCustomPower) pow;
                        cpow.onCardRedraw(c);
                    } catch (Exception e) {
                        DefaultMod.logger.warn(e);
                    }

                }

//                var4 = this.relics.iterator();
//
//                while(var4.hasNext()) {
//                    AbstractRelic r = (AbstractRelic)var4.next();
//                    r.onCardDraw(c);
//                }
            }
        }

    }

    private void endActionWithFollowUp() {
        this.isDone = true;
        if (this.followUpAction != null) {
            this.addToTop(this.followUpAction);
        }

    }
}

