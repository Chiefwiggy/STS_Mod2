package dkSTS.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.exordium.LouseNormal;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dkSTS.powers.Helpers.PowerData;
import dkSTS.powers.Helpers.PowerDataBuilder;

import java.util.ArrayList;

public class FutureSightPower extends AbstractCustomPower {
    public static PowerData data =  new PowerDataBuilder()
            .id(FutureSightPower.class)
            .img_path("placeholder_power")
            .buff()
            .build();

    public FutureSightPower(final AbstractCreature owner, final AbstractCreature source) {
        super(data, owner, source, -1);
    }

    @Override
    public AbstractPower makeCopy() {
        return new FutureSightPower(owner, source);
    }

    @Override
    public void atStartOfTurnPostDraw() {
//
    }
}
