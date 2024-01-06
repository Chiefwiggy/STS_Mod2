package dkSTS.powers;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ThornsPower;
import dkSTS.powers.Helpers.PowerData;
import dkSTS.powers.Helpers.PowerDataBuilder;

public class WiltingThornsPower extends AbstractCustomPower {
    public static PowerData data = new PowerDataBuilder()
            .id(WiltingThornsPower.class)
            .img_path("placeholder_power")
            .debuff()
            .notTurnBased()
            .build();

    public WiltingThornsPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        super(data, owner, source, amount);
    }

    @Override
    public AbstractPower makeCopy() {
        return new WiltingThornsPower(owner, source, amount);
    }

    @Override
    public void updateDescription() {
        description = DESC[0] + this.amount + DESC[1];
    }

    @Override
    public void wasHPLost(DamageInfo info, int damageAmount) {
        AbstractPlayer p = AbstractDungeon.player;
        if (info.owner != null && info.type != DamageInfo.DamageType.HP_LOSS && info.type != DamageInfo.DamageType.THORNS && damageAmount >= 1) {
            this.addToTop(
                    new ReducePowerAction(p, p, ThornsPower.POWER_ID, this.amount)
            );
            this.addToTop(
                    new RemoveSpecificPowerAction(p, p, this)
            );
        }
    }
}
