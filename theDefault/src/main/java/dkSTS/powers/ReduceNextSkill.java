package dkSTS.powers;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.unique.DiscoveryAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.colorless.Madness;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dkSTS.powers.Helpers.PowerData;
import dkSTS.powers.Helpers.PowerDataBuilder;

import java.util.ArrayList;

public class ReduceNextSkill extends AbstractCustomPower {
    public static PowerData data = new PowerDataBuilder()
            .id(ReduceNextSkill.class)
            .img_path("placeholder_power")
            .buff()
            .notTurnBased()
            .build();


    private final ArrayList<AbstractCard> preZeroCards;
    public ReduceNextSkill(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        super(data, owner, source, amount);

        preZeroCards = new ArrayList<>();
    }

    @Override
    public AbstractPower makeCopy() {
        return new ReduceNextSkill(owner, source, amount);
    }

    @Override
    public void onInitialApplication() {
        AbstractPlayer p = AbstractDungeon.player;

        for (AbstractCard c : p.hand.getSkills().group) {
            if (c.costForTurn == 0 && c.isCostModifiedForTurn) {
                preZeroCards.add(c);
            } else {
                c.costForTurn = Math.max(0, c.costForTurn-1);
                c.isCostModifiedForTurn = c.cost != c.costForTurn;
            }
        }
        for (AbstractCard c : p.drawPile.getSkills().group) {
            if (c.costForTurn == 0 && c.isCostModifiedForTurn) {
                preZeroCards.add(c);
            } else {
                c.costForTurn = Math.max(0, c.costForTurn-1);
                c.isCostModifiedForTurn = c.cost != c.costForTurn;
            }
        }
        for (AbstractCard c : p.discardPile.getSkills().group) {
            if (c.costForTurn == 0 && c.isCostModifiedForTurn) {
                preZeroCards.add(c);
            } else {
                c.costForTurn = Math.max(0, c.costForTurn-1);
                c.isCostModifiedForTurn = c.cost != c.costForTurn;
            }
        }
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        RemoveSelf();
    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        if (card.type == AbstractCard.CardType.SKILL) {
            this.addToBot(
                    new ReducePowerAction(owner, source, this, 1)
            );
        }
    }




    @Override
    public void onRemove() {
        AbstractPlayer p = AbstractDungeon.player;

        for (AbstractCard c : p.hand.getSkills().group) {
            c.costForTurn = Math.min(c.cost, c.costForTurn+1);
            c.isCostModifiedForTurn = c.cost != c.costForTurn;
        }
        for (AbstractCard c : p.drawPile.getSkills().group) {
            c.costForTurn = Math.min(c.cost, c.costForTurn+1);
            c.isCostModifiedForTurn = c.cost != c.costForTurn;
        }
        for (AbstractCard c : p.discardPile.getSkills().group) {
            c.costForTurn = Math.min(c.cost, c.costForTurn+1);
            c.isCostModifiedForTurn = c.cost != c.costForTurn;
        }
        for (AbstractCard c : preZeroCards) {
            c.costForTurn = 0;
            c.isCostModifiedForTurn = true;
        }
        preZeroCards.clear();
    }

    @Override
    public void updateDescription() {
        description = DESC[0] + SwitchPlural(1, 2, true) + DESC[3];
    }
}
