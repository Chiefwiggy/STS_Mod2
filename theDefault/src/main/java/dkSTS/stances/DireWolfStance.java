package dkSTS.stances;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.evacipated.cardcrawl.mod.stslib.patches.core.AbstractCreature.TempHPField;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.RemoveAllBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import dkSTS.DefaultMod;
import dkSTS.cards.Helpers.ApplyPowerBuilder;
import dkSTS.effects.AbstractStanceAuraEffect;
import dkSTS.effects.AbstractStanceParticleEffect;
import dkSTS.effects.BloodlustAuraEffect;
import dkSTS.effects.BloodlustParticleEffect;
import dkSTS.stances.Helpers.StanceBuilder;
import dkSTS.stances.Helpers.StanceData;

public class DireWolfStance extends AbstractBruxaStance {

    public static StanceData data = new StanceBuilder()
            .id(DireWolfStance.class)
            .build();

    private static final int DAMAGE_PERCENT = 25;
    private static final int WOLF_POWER = 50;

    private static final int WARD_PERCENT = 50;

    public DireWolfStance() {
        super(data);
    }

    @Override
    public void onEnterStance() {
        AbstractPlayer p = AbstractDungeon.player;
        this.addToBot(
                new AddTemporaryHPAction(p,p, MathUtils.floor(p.maxHealth*0.5f))
        );
        this.addToBot(
                new RemoveAllBlockAction(p, p)
        );
    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        if (type == DamageInfo.DamageType.NORMAL) {
            return damage * 1.25f;
        }
        return damage;
    }


    @Override
    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
        int remainingWard = TempHPField.tempHp.get(AbstractDungeon.player);
        if (remainingWard == 0) {
            this.exitStance();
        }
        return super.onAttackedToChangeDamage(info, damageAmount);
    }

    @Override
    public float modifyBlock(float blockAmount, AbstractCard c) {
        return 0;
    }

    @Override
    public void onExitStance() {
        this.addToBot(
                new MakeTempCardInDiscardAction(new Dazed(), 5)
        );
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
