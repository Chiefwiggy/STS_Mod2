package dkSTS.cards.Attacks.Uncommon;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;
import dkSTS.cards.Helpers.DamageActionBuilder;
import dkSTS.powers.FrostPower;

import java.util.Iterator;

public class BreachingBlast extends AbstractBruxaCard {

    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(BreachingBlast.class)
            .img("BreachingBlast.png")
            .rarity(CardRarity.UNCOMMON)
            .target(CardTarget.ENEMY)
            .build();

    private static final int DAMAGE = 5;

    private static final int MULTIPLIER = 3;
    private static final int MULTIPLIER_UPGRADE = 1;

    public BreachingBlast() {
        super(data);

        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MULTIPLIER;
    }
    private int getMultiplier(AbstractMonster m) {
        return m.hasPower(FrostPower.data.POWER_ID) ? magicNumber : 1;
    }

    @Override
    protected void UpgradeParameters() {
        upgradeMagicNumber(MULTIPLIER_UPGRADE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(
                new DamageActionBuilder()
                        .damage(damage)
                        .target(m)
                        .animation(AbstractGameAction.AttackEffect.BLUNT_HEAVY)
                        .build()
        );
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int realBaseDamage = this.baseDamage;
        this.baseDamage = getMultiplier(mo) * realBaseDamage;
        super.calculateCardDamage(mo);
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }

    @Override
    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!m.isDeadOrEscaped() && m.hasPower(FrostPower.data.POWER_ID)) {
                this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
                break;
            }
        }
    }
}
