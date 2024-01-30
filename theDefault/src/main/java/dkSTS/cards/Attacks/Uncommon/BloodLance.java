package dkSTS.cards.Attacks.Uncommon;

import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dkSTS.DefaultMod;
import dkSTS.cards.Abstracts.AbstractDrainAndFluxCard;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;
import dkSTS.cards.Helpers.DamageActionBuilder;

public class BloodLance extends AbstractDrainAndFluxCard {

    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(BloodLance.class)
            .img("BloodLance.png")
            .cost(1)
            .type(CardType.ATTACK)
            .rarity(CardRarity.UNCOMMON)
            .target(CardTarget.ENEMY)
            .build();

    private static final int DAMAGE = 8;
    private static final int DAMAGE_UPGRADE = 2;
    private static final int DRAIN = 5;
    private static final int DRAIN_UPGRADE = 2;

    public BloodLance() {
        super(data);

        baseDamage = damage = DAMAGE;
        baseHeal = heal = DRAIN;
    }

    @Override
    protected void UpgradeParameters() {
        upgradeDamage(DAMAGE_UPGRADE);
        upgradeHeal(DRAIN_UPGRADE);
    }

    @Override
    protected boolean doDrainDamage() {
        return isFlux();
    }

    @Override
    protected boolean doesReduceHunger() {
        return isFlux();
    }

    @Override
    public void postHungerUseAlways(AbstractPlayer p, AbstractMonster m) {
        new DamageActionBuilder()
                .damage(damage)
                .target(m)
                .addToBottom();
    }

    @Override
    public void useOnFlux(AbstractPlayer p, AbstractMonster m) {
        addToBottom(
                new HealAction(p, p, heal)
        );
    }
}
