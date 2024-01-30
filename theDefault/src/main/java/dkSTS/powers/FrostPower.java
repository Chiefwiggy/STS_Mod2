package dkSTS.powers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dkSTS.powers.Helpers.PowerData;
import dkSTS.powers.Helpers.PowerDataBuilder;
import dkSTS.relics.BlastHammerRelic;
import dkSTS.relics.RimefrostPendantRelic;

public class FrostPower extends AbstractCountdownPower {

    public static PowerData data = new PowerDataBuilder()
            .id(FrostPower.class)
            .img_path("frost")
            .debuff()
            .turnBased()
            .build();

    private static final int TURN_TO_FROZEN = 8;




    public FrostPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        super(data, owner, source, amount);

        this.priority = 100;
    }


    @Override
    public AbstractPower makeCopy() {
        return new FrostPower(owner, source, amount);
    }

    @Override
    public void updateDescription() {
        int damagePercent = 15;
        int reducePercent = 15;

        if (AbstractDungeon.player.hasRelic(BlastHammerRelic.data.ID)) {
            damagePercent = 40;
        }
        if (AbstractDungeon.player.hasRelic(RimefrostPendantRelic.data.ID)) {
            reducePercent = 25;
        }
        description = DESC[0] + reducePercent + DESC[1] + damagePercent + DESC[2] +  amount + SwitchPlural(3, 4) + DESC[5];
    }

    @Override
    public void onRemove() {
        super.onRemove();
        this.owner.tint.changeColor(Color.WHITE.cpy());
    }

    public float atDamageGive(float damage, DamageType type) {
        if (type == DamageType.NORMAL) {
            if (AbstractDungeon.player.hasRelic(RimefrostPendantRelic.data.ID)) {
                return MathUtils.floor(damage * 0.75F);
            }
            return (float)Math.floor(damage * 0.85F);
        } else {
            return damage;
        }
    }

    @Override
    public float atDamageReceive(float damage, DamageType damageType) {
        if (damageType == DamageType.NORMAL) {
            if (AbstractDungeon.player.hasRelic(BlastHammerRelic.data.ID)) {
                return MathUtils.floor(damage * 1.4F);
            }
            return (float)Math.floor(damage * 1.15F);
        } else {
            return damage;
        }
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        if (this.amount >= TURN_TO_FROZEN) {
            this.addToBot(
                    new ReducePowerAction(
                            owner, owner, this, TURN_TO_FROZEN
                    )
            );
            this.addToBot(
                    new ApplyPowerAction(owner, owner,
                            new FrozenPower(
                                    owner, owner, 1
                            ), 1
                    )
            );
        }
    }
}
