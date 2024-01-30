package dkSTS.cards.Attacks.Common;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.cards.Abstracts.AbstractWildCard;
import dkSTS.cards.Helpers.ApplyPowerBuilder;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;
import dkSTS.cards.Helpers.DamageActionBuilder;
import dkSTS.powers.ReduceNextSkill;

public class Swipe extends AbstractWildCard {
    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(Swipe.class)
            .img("Swipe.png")
            .rarity(CardRarity.COMMON)
            .type(CardType.ATTACK)
            .target(CardTarget.ENEMY)
            .cost(1)
            .build();

    private static final int DAMAGE =4;
    private static final int UPGRADE_DMG = 3;
    public Swipe() {
        super(data);

        baseDamage = damage = DAMAGE;
    }

    @Override
    protected void UpgradeParameters() {
        upgradeDamage(UPGRADE_DMG);
    }

    @Override
    public void wildUse(AbstractPlayer p, AbstractMonster m) {
        new DamageActionBuilder()
                .target(m)
                .damage(damage)
                .addToBottom();

        new ApplyPowerBuilder()
                .power(ReduceNextSkill.class)
                .amount(1)
                .addToBottom();

    }
}
