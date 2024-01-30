package dkSTS.cards.Attacks.Common;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.cards.Helpers.ApplyPowerBuilder;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;
import dkSTS.cards.Helpers.DamageActionBuilder;
import dkSTS.powers.FrostPower;

public class IcicleInferno extends AbstractBruxaCard {
    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(IcicleInferno.class)
            .type(CardType.ATTACK)
            .rarity(CardRarity.COMMON)
            .target(CardTarget.ENEMY)
            .cost(1)
            .img("Shatter.png")
            .build();

    private static final int DAMAGE = 7;
    private static final int DAMAGE_UPGRADE = 3;

    private static final int CHILL = 1;
    private static final int CHILL_UPGRADE = 1;

    public IcicleInferno() {
        super(data);

        cardsToPreview = new Burn();
        baseMagicNumber = magicNumber = CHILL;
        baseDamage = damage = DAMAGE;
    }

    @Override
    protected void UpgradeParameters() {
        upgradeDamage(DAMAGE_UPGRADE);
        upgradeMagicNumber(CHILL_UPGRADE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        new DamageActionBuilder()
                .damage(damage)
                .target(m)
                .addToBottom();

        new ApplyPowerBuilder()
                .targetAll()
                .power(FrostPower.class)
                .amount(magicNumber)
                .addToBottom();

        this.addToBottom(
                new MakeTempCardInDiscardAction(new Burn(), 1)
        );
    }
}
