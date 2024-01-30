package dkSTS.powers;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.watcher.DivinePunishmentAction;
import com.megacrit.cardcrawl.cards.purple.Blasphemy;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.stances.AbstractStance;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import dkSTS.powers.Helpers.PowerData;
import dkSTS.powers.Helpers.PowerDataBuilder;
import dkSTS.stances.DireWolfStance;

public class LupineMutagenPower extends AbstractCustomPower {
    public static PowerData data = new PowerDataBuilder()
            .id(LupineMutagenPower.class)
            .img_path("placeholder_path")
            .notTurnBased()
            .debuff()
            .build();

    public LupineMutagenPower(final AbstractCreature owner, final AbstractCreature source) {
        super(data, owner, source, -1);
    }

    @Override
    public AbstractPower makeCopy() {
        return new LupineMutagenPower(owner, source);
    }

    @Override
    public void onChangeStance(AbstractStance oldStance, AbstractStance newStance) {
        if (oldStance.ID.equals(DireWolfStance.data.ID)) {
            this.flash();
            this.addToBot(new VFXAction(new LightningEffect(this.owner.hb.cX, this.owner.hb.cY)));
            this.addToBot(new LoseHPAction(this.owner, this.owner, 99999));
            this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
        }
    }

    @Override
    public void updateDescription() {
        description = DESC[0];
    }
}
