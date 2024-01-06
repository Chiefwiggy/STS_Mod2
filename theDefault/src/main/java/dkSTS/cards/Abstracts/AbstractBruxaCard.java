package dkSTS.cards.Abstracts;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dkSTS.DefaultMod;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.powers.AbstractCustomPower;

public abstract class AbstractBruxaCard extends AbstractDynamicCard {

    private final String UPGRADE_DESCRIPTION;
    public AbstractBruxaCard(BruxaCardData data) {
        super(data.ID, DefaultMod.makeCardPath(data.IMG), data.COST, data.TYPE, data.COLOR, data.RARITY, data.TARGET);
        UPGRADE_DESCRIPTION = CardCrawlGame.languagePack.getCardStrings(data.ID).UPGRADE_DESCRIPTION;
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

    protected void UpgradeDescription() {
        rawDescription = UPGRADE_DESCRIPTION;
    }

    protected <T extends AbstractPower> void gainPower(Class<T> appliedClass, final int amount) {
        AbstractPlayer p = AbstractDungeon.player;
        try {
            addToBottom(
                    new ApplyPowerAction(p, p, appliedClass.getDeclaredConstructor(AbstractCreature.class, AbstractCreature.class, Integer.class).newInstance(p, p, amount))
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected <T extends AbstractCustomPower> void applyPower(Class<T> appliedClass, final AbstractMonster m, final int amount) {
        AbstractPlayer p = AbstractDungeon.player;
        try {
            addToBottom(
                    new ApplyPowerAction(m, p, appliedClass.getDeclaredConstructor(AbstractCreature.class, AbstractCreature.class, Integer.class).newInstance(m, p, amount))
            );
        } catch (Exception e) {
            e.printStackTrace();
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
