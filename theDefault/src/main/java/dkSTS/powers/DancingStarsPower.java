package dkSTS.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dkSTS.cards.Helpers.ApplyPowerBuilder;
import dkSTS.powers.Helpers.PowerData;
import dkSTS.powers.Helpers.PowerDataBuilder;

public class DancingStarsPower extends AbstractCustomPower {
    public static PowerData data = new PowerDataBuilder()
            .id(DancingStarsPower.class)
            .img_path("placeholder_power")
            .buff()
            .notTurnBased()
            .build();

    public DancingStarsPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        super(data, owner, source, amount);
    }

    @Override
    public AbstractPower makeCopy() {
        return new DancingStarsPower(owner, source, amount);
    }

    @Override
    public void atStartOfTurn() {
        for (int i = 0; i < amount; ++i) {
            AbstractMonster target = AbstractDungeon.getCurrRoom().monsters.getRandomMonster(true);
            new ApplyPowerBuilder()
                    .power(ExposePower.class)
                    .amount(1)
                    .target(target)
                    .addToBottom();
        }
    }

    @Override
    public void updateDescription() {
        description = DESC[0] + SwitchPlural(1, 2, true);
    }
}
