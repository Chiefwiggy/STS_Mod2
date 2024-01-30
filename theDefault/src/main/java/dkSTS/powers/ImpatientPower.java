package dkSTS.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.blue.ConserveBattery;
import com.megacrit.cardcrawl.cards.blue.Electrodynamics;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.*;
import dkSTS.actions.SicWolvesAction;
import dkSTS.powers.Helpers.PowerData;
import dkSTS.powers.Helpers.PowerDataBuilder;

public class ImpatientPower extends AbstractCustomPower{
    public static PowerData data = new PowerDataBuilder()
            .id(ImpatientPower.class)
            .img_path("impatient")
            .notTurnBased()
            .buff()
            .build();

    public ImpatientPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        super(data, owner, source, amount);

        this.amount2 = amount;

        canGoNegative2 = true;
    }


    @Override
    public void atStartOfTurn() {
        amount2 = amount;
        showZero2 = true;
        updateDescription();

    }

    @Override
    public AbstractPower makeCopy() {
        return new ImpatientPower(owner, source, amount);
    }


    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (amount2 > 0) {
            if (target == owner) {
                if (power instanceof NextTurnBlockPower) {
                    this.addToBot(
                            new GainBlockAction(owner, power.amount)
                    );
                    amount2--;
                    this.flash();
                } else if (power instanceof EnergizedPower || power instanceof EnergizedBluePower) {
                    this.addToBot(
                            new GainEnergyAction(power.amount)
                    );
                    amount2--;
                    this.flash();
                } else if (power instanceof DrawCardNextTurnPower) {
                    this.addToBot(
                            new DrawCardAction(owner, power.amount)
                    );
                    amount2--;
                    this.flash();
                } else if (power instanceof NextTurnPlayCardPower) {
                    ((NextTurnPlayCardPower)power).playInstancesOfCard();
                } else if (power instanceof SicWolvesNextTurnPower) {
                    for (int i = 0; i < power.amount; ++i) {
                        this.addToBot(
                                new SicWolvesAction()
                        );
                    }
                    amount2--;
                    this.flash();
                }
            }
        }
        updateDescription();
    }

    @Override
    public void updateDescription() {
        description = DESC[0] + SwitchPlural(1, 2, true) + DESC[3] + amount2 + DESC[4];
    }
}
