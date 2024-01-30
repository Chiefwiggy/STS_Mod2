package dkSTS.cards.Attacks.Uncommon;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import dkSTS.cards.Abstracts.AbstractDrainCard;
import dkSTS.cards.Helpers.ApplyPowerBuilder;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;
import dkSTS.cards.Helpers.DamageActionBuilder;
import dkSTS.powers.FrostPower;

public class IceBite extends AbstractDrainCard {
    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(IceBite.class)
            .img("IceBite.png")
            .rarity(CardRarity.UNCOMMON)
            .type(CardType.ATTACK)
            .cost(1)
            .target(CardTarget.ENEMY)
            .build();

    private static final int DAMAGE = 3;

    private static final int FROST = 2;

    public IceBite() {
        super(data);

        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = FROST;
        baseHeal = heal = FROST;
    }

    @Override
    protected void UpgradeParameters() {
        UpgradeDescription();
    }

    private int getEnemyFrost(AbstractMonster m) {
        if (m.hasPower(FrostPower.data.POWER_ID)) {
            return m.getPower(FrostPower.data.POWER_ID).amount;
        } else if (m.hasPower(ArtifactPower.POWER_ID)) {
            return -baseHeal;
        }
        return 0;
    }
    @Override
    public void postHungerUse(AbstractPlayer p, AbstractMonster m) {
        int healAmount = MathUtils.floor((heal + getEnemyFrost(m))*(upgraded ? 1.0F : 0.5F));
        addToBottom(
                new HealAction(p,p, healAmount)
        );
        new DamageActionBuilder()
                .damage(damage)
                .target(m)
                .animation(AbstractGameAction.AttackEffect.SLASH_HORIZONTAL)
                .addToBottom();

        new ApplyPowerBuilder()
                .power(FrostPower.class)
                .amount(magicNumber)
                .target(m)
                .addToBottom();

    }
}
