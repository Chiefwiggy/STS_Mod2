package dkSTS.actions;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import dkSTS.cards.Helpers.ApplyPowerBuilder;
import dkSTS.powers.VitalityPower;
import dkSTS.powers.WolfPower;

public class DevourWolvesAction extends AbstractGameAction {

    int strGain;
    boolean hasWolves;
    AbstractPlayer p;

    public DevourWolvesAction() {
        p = AbstractDungeon.player;
        strGain = 0;
        hasWolves = false;
        int mult = 1;
        if (p.hasPower(WolfPower.data.POWER_ID)) {
            strGain = MathUtils.floor(.5f * p.getPower(WolfPower.data.POWER_ID).amount);
            hasWolves = true;
        }
        this.actionType = ActionType.POWER;
        source = p;
    }

    @Override
    public void update() {
        if (hasWolves) {
            WolfPower wp =(WolfPower)p.getPower(WolfPower.data.POWER_ID);
            this.addToTop(
                    new RemoveSpecificPowerAction(p, p, wp)
            );
            if (strGain > 0) {
                new ApplyPowerBuilder()
                        .power(StrengthPower.class)
                        .amount(strGain)
                        .addToTop();
            }

        }
        this.isDone = true;
    }
}
