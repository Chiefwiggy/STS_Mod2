package dkSTS.actions;

import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.stslib.patches.cardInterfaces.BranchingUpgradesPatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import dkSTS.DefaultMod;
import dkSTS.cards.Helpers.DamageActionBuilder;
import dkSTS.powers.FeralPower;
import dkSTS.powers.WolfPower;
import dkSTS.stances.DireWolfStance;

import java.util.ArrayList;

public class SicWolvesAction extends AbstractGameAction {

    private int wolvesLeft;
    private boolean hasSearchedWolves = false;
    private int wolfDamage;
    public SicWolvesAction() {
        this(AbstractDungeon.player);
    }
    public SicWolvesAction(final AbstractCreature ac) {
        source = ac;
        this.actionType = ActionType.DAMAGE;
        this.duration = 0.1F;
        hasSearchedWolves = false;
        wolvesLeft = 0;
        wolfDamage = 2;
    }
    @Override
    public void update() {
        if (!hasSearchedWolves) {
            if (!source.hasPower(WolfPower.data.POWER_ID)) {
                this.isDone = true;
            } else {
                wolvesLeft = source.getPower(WolfPower.data.POWER_ID).amount;
            }
            if (source.hasPower(FeralPower.data.POWER_ID)) {
                wolfDamage += source.getPower(FeralPower.data.POWER_ID).amount;
            }
            if (AbstractDungeon.player.stance.ID.equals(DireWolfStance.data.ID)) {
                wolfDamage = MathUtils.round(wolfDamage*1.5f);
            }
            hasSearchedWolves = true;
        }

        if (wolvesLeft == 0) {
            isDone = true;
        } else {
            if (!AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                wolvesLeft--;
                new DamageActionBuilder()
                        .damage(wolfDamage)
                        .damageType(DamageInfo.DamageType.THORNS)
                        .targetRandom()
                        .animation(AttackEffect.SLASH_HORIZONTAL)
                        .addToTop();

            } else {
                this.isDone = true;
            }
        }

        DefaultMod.logger.info("XXX: " + isDone);
    }
}
