package dkSTS.stances;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.actions.watcher.NotStanceCheckAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.BufferPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.EmptyStanceEffect;
import dkSTS.DefaultMod;
import dkSTS.cards.Helpers.ApplyPowerBuilder;
import dkSTS.effects.*;
import dkSTS.powers.CloakPower;
import dkSTS.stances.Helpers.StanceBuilder;
import dkSTS.stances.Helpers.StanceData;

public class InvisibilityStance extends AbstractBruxaStance {
    public static StanceData data = new StanceBuilder()
            .id(InvisibilityStance.class)
            .build();

    private int turnsTillEmpty;
    public InvisibilityStance() {
        super(data);

        turnsTillEmpty = 5;
        updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        turnsTillEmpty--;
        if (turnsTillEmpty == 0) {
            exitStance();
        }
        updateDescription();
    }

    @Override
    public void onEndOfTurn() {
        if (EnergyPanel.getCurrentEnergy() == 0) {
            this.exitStance();
        } else {
            new ApplyPowerBuilder()
                    .power(CloakPower.class)
                    .amount(1)
                    .addToBottom();
        }
    }

    @Override
    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
        DefaultMod.logger.warn("DAMAGEEEEEEEEEEEEEEEEEE: " + damageAmount);
        if (damageAmount > 0) {
            this.exitStance();
        }
        return super.onAttackedToChangeDamage(info, damageAmount);
    }

    @Override
    public void updateDescription() {
        if (turnsTillEmpty == 1) {
            description = data.DESCRIPTION[0] + turnsTillEmpty + data.DESCRIPTION[1] + data.DESCRIPTION[3];
        } else {
            description = data.DESCRIPTION[0] + turnsTillEmpty + data.DESCRIPTION[2] + data.DESCRIPTION[3];
        }

    }

    @Override
    protected AbstractStanceParticleEffect generateParticle() {
        return new InvisibilityParticleEffect();
    }

    @Override
    protected AbstractStanceAuraEffect generateAura() {
        return new InvisibilityAuraEffect();
    }

    @Override
    protected BorderFlashEffect generateBorderFlash() {
        return new BorderFlashEffect(Color.WHITE, true);
    }

}
