package dkSTS.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Frost;
import dkSTS.DefaultMod;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;
import dkSTS.cards.Helpers.DamageActionBuilder;
import dkSTS.powers.FrostPower;


public class Frostbite extends AbstractBruxaCard {

    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(Frostbite.class)
            .img("Attack.png")
            .rarity(CardRarity.BASIC)
            .target(CardTarget.ENEMY)
            .build();

    private static final int DAMAGE = 4;
    private static final int DAMAGE_UPGRADE = 2;

    private static final int FROST = 2;
    private static final int FROST_UPGRADE = 1;

    public Frostbite() {
        super(data);

        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = FROST;
    }

    @Override
    protected void UpgradeParameters() {
        upgradeDamage(DAMAGE_UPGRADE);
        upgradeMagicNumber(FROST_UPGRADE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBottom(
                new DamageActionBuilder()
                        .damage(damage)
                        .target(m)
                        .animation(AbstractGameAction.AttackEffect.LIGHTNING)
                        .build()
        );

        addToBottom(
                new ApplyPowerAction(m, p, new FrostPower(m, p, magicNumber), magicNumber)
        );
    }


}
