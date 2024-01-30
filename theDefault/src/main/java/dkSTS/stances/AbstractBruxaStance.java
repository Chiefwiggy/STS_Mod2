package dkSTS.stances;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.actions.watcher.NotStanceCheckAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.stances.AbstractStance;
import com.megacrit.cardcrawl.stances.WrathStance;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.EmptyStanceEffect;
import com.megacrit.cardcrawl.vfx.stance.StanceAuraEffect;
import com.megacrit.cardcrawl.vfx.stance.WrathParticleEffect;
import dkSTS.DefaultMod;
import dkSTS.effects.AbstractStanceAuraEffect;
import dkSTS.effects.AbstractStanceParticleEffect;
import dkSTS.effects.BloodlustAuraEffect;
import dkSTS.effects.BloodlustParticleEffect;
import dkSTS.stances.Helpers.StanceData;

public abstract class AbstractBruxaStance extends AbstractStance {

    private final StanceData stanceData;
    public AbstractBruxaStance(StanceData data) {
        this.ID = data.ID;
        this.name = data.NAME;
        this.stanceData = data;

        updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = stanceData.DESCRIPTION[0];
    }

    @Override
    public void onEnterStance() {
        AbstractDungeon.effectsQueue.add(generateBorderFlash());
    }



    public void updateAnimation() {
        if (!Settings.DISABLE_EFFECTS) {
            this.particleTimer -= Gdx.graphics.getDeltaTime();
            if (this.particleTimer < 0.0F) {
                this.particleTimer = 0.05F;
                AbstractDungeon.effectsQueue.add(generateParticle());
            }
        }

        this.particleTimer2 -= Gdx.graphics.getDeltaTime();
        if (this.particleTimer2 < 0.0F) {
            this.particleTimer2 = MathUtils.random(0.3F, 0.4F);
            AbstractDungeon.effectsQueue.add(generateAura());
        }
    }

    protected abstract AbstractStanceParticleEffect generateParticle();
    protected abstract AbstractStanceAuraEffect generateAura();
    protected abstract BorderFlashEffect generateBorderFlash();

    public void onAfterCardPlayed(AbstractCard card) {}

    public void onMonsterAttacked(AbstractMonster mon, DamageInfo info, int damageAmount) {}

    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) { return damageAmount;}


    protected void addToBot(AbstractGameAction action) {
        AbstractDungeon.actionManager.addToBottom(action);
    }

    protected void addToTop(AbstractGameAction action) {
        AbstractDungeon.actionManager.addToTop(action);
    }



    protected final void exitStance() {
        AbstractPlayer p = AbstractDungeon.player;
        this.addToBot(new NotStanceCheckAction("Neutral", new VFXAction(new EmptyStanceEffect(p.hb.cX, p.hb.cY), 0.1F)));
        this.addToBot(new ChangeStanceAction("Neutral"));
    }

    public float modifyBlock(float blockAmount, AbstractCard c) { return blockAmount;}
}
