package dkSTS.cards.Attacks.Starter;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.cards.Abstracts.AbstractDrainCard;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;
import dkSTS.cards.Helpers.DamageActionBuilder;


public class Bite extends AbstractDrainCard {

    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(Bite.class)
            .img("VampiricBite.png")
            .rarity(CardRarity.BASIC)
            .target(CardTarget.ENEMY)
            .build();



    private static final int DAMAGE = 5;
    private static final int DAMAGE_UPGRADE = 1;

    private static final int HEAL = 2;
    private static final int HEAL_UPGRADE = 1;

    public Bite() {
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
