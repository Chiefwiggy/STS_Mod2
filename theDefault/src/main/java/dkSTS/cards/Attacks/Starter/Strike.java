package dkSTS.cards.Attacks.Starter;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;
import dkSTS.cards.Helpers.DamageActionBuilder;

public class Strike extends AbstractBruxaCard {

    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(Strike.class)
            .img("Strike.png")
            .rarity(CardRarity.BASIC)
            .target(CardTarget.ENEMY)
            .build();

    private static final int DAMAGE = 6;
    private static final int DAMAGE_UPGRADE = 3;

    public Strike() {
        super(data);

        baseDamage = DAMAGE;

        AddTag(CardTags.STARTER_STRIKE);
        AddTag(CardTags.STRIKE);
    }

    @Override
    protected void UpgradeParameters() {
        upgradeDamage(DAMAGE_UPGRADE);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBottom(
                new DamageActionBuilder()
                        .damage(damage)
                        .target(abstractMonster)
                        .animation(AbstractGameAction.AttackEffect.SLASH_HORIZONTAL)
                        .build()
        );
    }
}
