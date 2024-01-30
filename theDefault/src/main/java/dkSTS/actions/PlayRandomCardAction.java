package dkSTS.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.UnlimboAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class PlayRandomCardAction extends AbstractGameAction {
    private boolean exhaustCards;
    private CardGroup group;
    private final AbstractCard.CardType type;
    public PlayRandomCardAction(final AbstractCreature target, final boolean exhaustCards, final int amount, final CardGroup group, AbstractCard.CardType type) {
        this.setValues(target, AbstractDungeon.player, amount);
        this.group = group;
        this.exhaustCards = exhaustCards;
        this.actionType = ActionType.WAIT;
        this.duration = Settings.ACTION_DUR_FAST;
        this.type = type;
    }

    public PlayRandomCardAction(final int amount, final CardGroup group, final AbstractCard.CardType type) {
        this(AbstractDungeon.getCurrRoom().monsters.getRandomMonster(null, true, AbstractDungeon.cardRandomRng), false, amount, group, type);
    }

    public PlayRandomCardAction(final int amount, final CardGroup group) {
        this(AbstractDungeon.getCurrRoom().monsters.getRandomMonster(null, true, AbstractDungeon.cardRandomRng), false, amount, group, null);
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (group.size() == 0) {
                this.isDone = true;
                return;
            }

            if (type != null && group.getCardsOfType(type).size() == 0) {
                this.isDone = true;
                return;
            }

            AbstractCard card;
            if (type == null) {
                card = group.getRandomCard(true);
            } else {
                card = group.getRandomCard(type, true);
            }
            group.removeCard(card);
            AbstractDungeon.getCurrRoom().souls.remove(card);
            card.exhaustOnUseOnce = this.exhaustCards;
            AbstractDungeon.player.limbo.group.add(card);
            card.current_y = -200.0F * Settings.scale;
            card.target_x = (float)Settings.WIDTH / 2.0F + 200.0F * Settings.xScale;
            card.target_y = (float)Settings.HEIGHT / 2.0F;
            card.targetAngle = 0.0F;
            card.lighten(false);
            card.drawScale = 0.12F;
            card.targetDrawScale = 0.75F;
            card.applyPowers();
            this.addToTop(new NewQueueCardAction(card, this.target, false, true));
            this.addToTop(new UnlimboAction(card));
            if (!Settings.FAST_MODE) {
                this.addToTop(new WaitAction(Settings.ACTION_DUR_MED));
            } else {
                this.addToTop(new WaitAction(Settings.ACTION_DUR_FASTER));
            }
            if (this.amount > 1) {
                this.addToTop(
                        new PlayRandomCardAction(target, exhaustCards, amount-1, group, type)
                );
            }
        }
        this.isDone = true;
    }
}
