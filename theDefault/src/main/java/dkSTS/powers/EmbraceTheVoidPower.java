package dkSTS.powers;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dkSTS.cards.Helpers.DamageActionBuilder;
import dkSTS.cards.Powers.Rare.EmbraceTheVoid;
import dkSTS.powers.Helpers.PowerData;
import dkSTS.powers.Helpers.PowerDataBuilder;

public class EmbraceTheVoidPower extends AbstractCustomPower {
    public static PowerData data = new PowerDataBuilder()
            .id(EmbraceTheVoidPower.class)
            .img_path("placeholder_power")
            .notTurnBased()
            .buff()
            .build();

    public EmbraceTheVoidPower(final AbstractCreature owner, final AbstractCreature source) {
        super(data, owner, source, -1);
    }

    @Override
    public void updateDescription() {
        description = DESC[0];
    }

    @Override
    public AbstractPower makeCopy() {
        return new EmbraceTheVoidPower(owner, source);
    }

    @Override
    public void atStartOfTurn() {
        new DamageActionBuilder()
                .target(AbstractDungeon.player)
                .damage(1)
                .damageType(DamageInfo.DamageType.HP_LOSS)
                .addToBottom();
    }
}
