package dkSTS.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;
import dkSTS.DefaultMod;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;
import dkSTS.cards.Helpers.DamageActionBuilder;


public class Bite extends AbstractBruxaCard {

    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(Bite.class)
            .img("Attack.png")
            .rarity(CardRarity.BASIC)
            .target(CardTarget.ENEMY)
            .build();



    private static final int DAMAGE = 7;
    private static final int DAMAGE_UPGRADE = 1;

    private static final int HEAL = 2;
    private static final int HEAL_UPGRADE = 1;

    public Bite() {
        super(data);

        baseDamage = DAMAGE;
        baseMagicNumber = HEAL;

    }

    @Override
    protected void UpgradeParameters() {
        upgradeDamage(DAMAGE_UPGRADE);
        upgradeMagicNumber(HEAL_UPGRADE);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster m) {

        this.addAnimation(m, BiteEffect.class, Settings.GOLD_COLOR.cpy());

        addToBottom(
                new DamageActionBuilder()
                        .damage(damage)
                        .target(m)
                        .animation(AbstractGameAction.AttackEffect.POISON)
                        .build()
        );

        addToBottom(
                new HealAction(abstractPlayer, abstractPlayer, magicNumber)
        );
    }
}
