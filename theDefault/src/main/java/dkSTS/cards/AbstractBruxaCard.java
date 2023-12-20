package dkSTS.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.ExplosionSmallEffect;
import dkSTS.DefaultMod;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.characters.TheBruxa;

public abstract class AbstractBruxaCard extends AbstractDynamicCard {

    public AbstractBruxaCard(BruxaCardData data) {
        super(data.ID, DefaultMod.makeCardPath(data.IMG), data.COST, data.TYPE, TheBruxa.Enums.COLOR_BRUXA, data.RARITY, data.TARGET);
    }

    protected void AddTag(CardTags tag) {
        this.tags.add(tag);
    }

    protected void addToBottom(AbstractGameAction action) {
        AbstractDungeon.actionManager.addToBottom(action);
    }

    protected void addToTop(AbstractGameAction action) {
        AbstractDungeon.actionManager.addToTop(action);
    }

    protected <T extends AbstractGameEffect> void addAnimation(AbstractCreature target, Class<T> effectClass, Object... otherArguments) {
        if (target != null) {
            float duration = Settings.FAST_MODE ? 0.1F : 0.3F;
            try {
                T effectInstance = effectClass.getDeclaredConstructor(float.class, float.class)
                        .newInstance(target.hb.cX, target.hb.cY - 40.0F * Settings.scale, otherArguments);
                this.addToBot(new VFXAction(effectInstance, duration));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected abstract void UpgradeParameters();

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            UpgradeParameters();
            initializeDescription();
        }
    }
}
