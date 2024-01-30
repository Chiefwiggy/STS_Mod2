package dkSTS.cards.Attacks.Uncommon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dkSTS.cards.Abstracts.AbstractFluxVoidFluxCard;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;
import dkSTS.cards.Helpers.DamageActionBuilder;

public class EchoStrike extends AbstractFluxVoidFluxCard {
    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(EchoStrike.class)
            .img("EchoStrike.png")
            .cost(1)
            .target(CardTarget.ENEMY)
            .type(CardType.ATTACK)
            .rarity(CardRarity.UNCOMMON)
            .build();

    private static final int DAMAGE = 7;
    private static final int DAMAGE_UPGRADE = 2;

    public EchoStrike() {
        super(data);

        baseDamage = damage = DAMAGE;

        tags.add(CardTags.STRIKE);
    }

    @Override
    protected void UpgradeParameters() {
        upgradeDamage(DAMAGE_UPGRADE);
    }

    @Override
    public void useAlways(AbstractPlayer p, AbstractMonster m) {
        new DamageActionBuilder()
                .target(m)
                .damage(damage)
                .addToBottom();
    }

    @Override
    public void useOnFlux(AbstractPlayer p, AbstractMonster m) {
        new DamageActionBuilder()
                .target(m)
                .damage(damage)
                .addToBottom();
    }

    @Override
    public void useOnVoidflux(AbstractPlayer p, AbstractMonster m) {
        new DamageActionBuilder()
                .target(m)
                .damage(damage)
                .addToBottom();
    }
}
