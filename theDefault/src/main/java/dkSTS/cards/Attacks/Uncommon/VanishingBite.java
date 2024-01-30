package dkSTS.cards.Attacks.Uncommon;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.cards.Abstracts.AbstractDrainAndFluxCard;
import dkSTS.cards.Abstracts.AbstractFluxCard;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;
import dkSTS.cards.Helpers.DamageActionBuilder;
import dkSTS.stances.InvisibilityStance;

public class VanishingBite extends AbstractDrainAndFluxCard {
    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(VanishingBite.class)
            .img("VanishingBite.png")
            .cost(1)
            .target(CardTarget.ENEMY)
            .type(CardType.ATTACK)
            .rarity(CardRarity.UNCOMMON)
            .build();

    private static final int DAMAGE = 5;
    private static final int DAMAGE_UPGRADE = 1;

    private static final int HEAL = 2;
    private static final int HEAL_UPGRADE = 1;

    public VanishingBite() {
        super(data);

        baseDamage = DAMAGE;
        baseHeal = heal = HEAL;
    }

    @Override
    protected void UpgradeParameters() {
        upgradeDamage(DAMAGE_UPGRADE);
        upgradeHeal(HEAL_UPGRADE);
    }

    @Override
    public void postHungerUseAlways(AbstractPlayer p, AbstractMonster m) {
        new DamageActionBuilder()
                .damage(damage)
                .target(m)
                .animation(AbstractGameAction.AttackEffect.POISON)
                .addToBottom();

        addToBottom(
                new HealAction(p,p, heal)
        );
    }

    @Override
    public void useOnFlux(AbstractPlayer p, AbstractMonster m) {
        this.addToBottom(
                new ChangeStanceAction(new InvisibilityStance())
        );
    }
}
