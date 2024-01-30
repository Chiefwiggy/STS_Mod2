package dkSTS.cards.HelperCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.cards.Abstracts.AbstractDrainCard;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;
import dkSTS.cards.Helpers.DamageActionBuilder;

public class AscendedBite extends AbstractDrainCard {
    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(AscendedBite.class)
            .img("Attack.png")
            .rarity(CardRarity.SPECIAL)
            .cost(1)
            .type(CardType.ATTACK)
            .color(CardColor.COLORLESS)
            .target(CardTarget.ENEMY)
            .build();

    public AscendedBite() {
        super(data);

        baseDamage = DAMAGE;
        baseHeal = heal = HEAL;
    }

    private static final int DAMAGE = 7;
    private static final int DAMAGE_UPGRADE = 2;

    private static final int HEAL = 4;
    private static final int HEAL_UPGRADE = 1;

    @Override
    protected void UpgradeParameters() {
        upgradeDamage(DAMAGE_UPGRADE);
        upgradeHeal(HEAL_UPGRADE);
    }

    @Override
    public void postHungerUse(AbstractPlayer abstractPlayer, AbstractMonster m) {

        //this.addAnimation(m, BiteEffect.class, Settings.GOLD_COLOR.cpy());

        new DamageActionBuilder()
                .damage(damage)
                .target(m)
                .animation(AbstractGameAction.AttackEffect.POISON)
                .addToBottom();

        addToBottom(
                new HealAction(abstractPlayer, abstractPlayer, heal)
        );
    }
}
