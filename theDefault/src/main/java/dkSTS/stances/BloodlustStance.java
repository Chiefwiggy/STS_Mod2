package dkSTS.stances;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.actions.watcher.NotStanceCheckAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.red.Reaper;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.EmptyStanceEffect;
import dkSTS.cards.HelperCards.HungerStatus;
import dkSTS.effects.AbstractStanceAuraEffect;
import dkSTS.effects.AbstractStanceParticleEffect;
import dkSTS.effects.BloodlustAuraEffect;
import dkSTS.effects.BloodlustParticleEffect;
import dkSTS.stances.Helpers.StanceBuilder;
import dkSTS.stances.Helpers.StanceData;

public class BloodlustStance extends AbstractBruxaStance {

    public static StanceData data = new StanceBuilder()
            .id(BloodlustStance.class)
            .build();

    private static final int ENERGY_GAINED = 1;
    public BloodlustStance() {
        super(data);
    }

    @Override
    public void onEnterStance() {

        AbstractPlayer p = AbstractDungeon.player;

        this.addToBot(
                new GainEnergyAction(ENERGY_GAINED)
        );
    }

    @Override
    public void onEndOfTurn() {
        this.exitStance();
    }

    @Override
    public void onMonsterAttacked(AbstractMonster mon, DamageInfo info, int damageAmount) {
        if (damageAmount > 0 && info.type == DamageInfo.DamageType.NORMAL) {
            addToBot(
                    new HealAction(AbstractDungeon.player, AbstractDungeon.player, MathUtils.floor((damageAmount)*0.5f))
            );
            addToBot(
                    new MakeTempCardInDiscardAction(
                            new HungerStatus(), 1
                    )
            );
        }
    }

    @Override
    protected AbstractStanceParticleEffect generateParticle() {
        return new BloodlustParticleEffect();
    }

    @Override
    protected AbstractStanceAuraEffect generateAura() {
        return new BloodlustAuraEffect();
    }

    @Override
    protected BorderFlashEffect generateBorderFlash() {
        return new BorderFlashEffect(Color.RED, true);
    }
}
