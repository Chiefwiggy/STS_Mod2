package dkSTS.cards.Abstracts;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
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
import dkSTS.powers.VitalityPower;

public abstract class AbstractBruxaCard extends AbstractDynamicCard {

    private final String UPGRADE_DESCRIPTION;
    protected final String[] EXTENDED_DESCRIPTIONS;
    protected final String ORIGINAL_DESCRIPTION;
    protected final String BASE_NAME;

    public int __baseMagicThird;
    public int __magicThird;
    public boolean upgradedMagicThird;
    public boolean isMagicThirdNumberModified;

    public boolean upgradedHeal;
    public boolean isHealModified;
    public AbstractBruxaCard(BruxaCardData data) {
        super(data.ID, DefaultMod.makeCardPath(data.IMG), data.COST, data.TYPE, data.COLOR, data.RARITY, data.TARGET);
        UPGRADE_DESCRIPTION = CardCrawlGame.languagePack.getCardStrings(data.ID).UPGRADE_DESCRIPTION;
        EXTENDED_DESCRIPTIONS = CardCrawlGame.languagePack.getCardStrings(data.ID).EXTENDED_DESCRIPTION;
        ORIGINAL_DESCRIPTION = CardCrawlGame.languagePack.getCardStrings(data.ID).DESCRIPTION;
        BASE_NAME = CardCrawlGame.languagePack.getCardStrings(data.ID).NAME;
        isMagicThirdNumberModified = false;
        isHealModified = false;
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

    @Override
    public void triggerWhenDrawn() {
        super.triggerWhenDrawn();
        applyPowers();
    }



    protected abstract void UpgradeParameters();

    @Override
    public void displayUpgrades() {
        super.displayUpgrades();
        if (upgradedMagicThird) {
            __magicThird = __baseMagicThird;
            isMagicThirdNumberModified = true;
        }
        if (upgradedHeal) {
            heal = baseHeal;
            isHealModified = true;
        }
    }

    public void upgradeHeal(int amount) {
        baseHeal += amount;
        heal = baseHeal;
        upgradedHeal = true;
    }

    public void upgradeMagicThird(int amount) {
        __baseMagicThird += amount;
        __magicThird = __baseMagicThird;
        upgradedMagicThird = true;
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            UpgradeParameters();
            initializeDescription();
        }
    }

    protected final boolean isPlayerBloodied() {
        AbstractPlayer p = AbstractDungeon.player;
        return p.currentHealth < p.maxHealth*0.5f;
    }

    public void onReceiveDamageWhileInHand(DamageInfo info, int damageAmount) {}
}
