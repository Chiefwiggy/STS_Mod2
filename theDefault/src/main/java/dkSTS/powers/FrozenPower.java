package dkSTS.powers;


import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dkSTS.powers.Helpers.PowerData;
import dkSTS.powers.Helpers.PowerDataBuilder;

import static com.badlogic.gdx.graphics.Color.*;

public class FrozenPower extends AbstractCountdownPower {
    public static PowerData data = new PowerDataBuilder()
            .id(FrozenPower.class)
            .img_path("placeholder_power")
            .debuff()
            .turnBased()
            .build();

    public FrozenPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        super(data, owner, source, amount);

        owner.tint.changeColor(BLUE.cpy());
    }

    @Override
    public void onRemove() {
        super.onRemove();
        this.owner.tint.changeColor(WHITE.cpy());
    }

    @Override
    public AbstractPower makeCopy() {
        return new FrozenPower(owner, source, amount);
    }

    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        if (type == DamageInfo.DamageType.NORMAL) {
            return 0;
        } else {
            return damage;
        }
    }

    @Override
    public float atDamageReceive(float damage, DamageInfo.DamageType damageType) {
        if (damageType == DamageInfo.DamageType.NORMAL) {
            return (float)Math.floor(damage * 2.0F);
        } else {
            return damage;
        }
    }

    @Override
    public void updateDescription() {
        description = DESC[0] + amount + SwitchPlural(1, 2);
    }


}
