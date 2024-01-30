package dkSTS.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.colorless.Bite;
import com.megacrit.cardcrawl.cards.red.Reaper;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;
import dkSTS.stances.BloodlustStance;

public class ConsumeVitalityAction  extends AbstractGameAction {

    private final DamageInfo di;
    private final int heal;

    public ConsumeVitalityAction(final AbstractMonster m, final DamageInfo di, final int heal_amt) {
        this.di = di;
        this.target = m;
        this.actionType = ActionType.DAMAGE;
        this.duration = 0.1F;
        this.heal = heal_amt;
        this.setValues(target, di);
    }

    @Override
    public void update() {
        this.target.damage(this.di);
        AbstractMonster m = (AbstractMonster) this.target;
        if ((m.isDying || this.target.currentHealth <= 0) && !this.target.halfDead && !this.target.hasPower("Minion")) {

            //add card
            AbstractPlayer p = AbstractDungeon.player;

            addToBot(new HealAction(p, p, heal));

            addToBot(
                    new ChangeStanceAction(new BloodlustStance())
            );

            if (this.target != null) {
                if (Settings.FAST_MODE) {
                    this.addToBot(new VFXAction(new BiteEffect(m.hb.cX, m.hb.cY - 40.0F * Settings.scale, Settings.GOLD_COLOR.cpy()), 0.1F));
                } else {
                    this.addToBot(new VFXAction(new BiteEffect(m.hb.cX, m.hb.cY - 40.0F * Settings.scale, Settings.GOLD_COLOR.cpy()), 0.3F));
                }
            }
        }

//        if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
//            AbstractDungeon.actionManager.clearPostCombatActions();
//        }

        this.isDone = true;
    }
}
