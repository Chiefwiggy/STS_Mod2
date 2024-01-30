package dkSTS.cards.Attacks.Rare;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.status.VoidCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.cards.Helpers.ApplyPowerBuilder;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;
import dkSTS.cards.Helpers.DamageActionBuilder;
import dkSTS.powers.FrostPower;

public class Voidchill extends AbstractBruxaCard {
    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(Voidchill.class)
            .img("VoidChill.png")
            .type(CardType.ATTACK)
            .rarity(CardRarity.RARE)
            .target(CardTarget.ENEMY)
            .cost(1)
            .build();

    private static final int DAMAGE = 12;
    private static final int DAMAGE_UPGRADE = 4;

    private static final int FROST = 4;
    private static final int FROST_UPGRADE = 1;

    public Voidchill() {
        super(data);

        baseDamage = damage = DAMAGE;
        baseMagicNumber = magicNumber = FROST;

        cardsToPreview = new VoidCard();
    }

    @Override
    protected void UpgradeParameters() {
        upgradeDamage(DAMAGE_UPGRADE);
        upgradeMagicNumber(FROST_UPGRADE);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        new DamageActionBuilder()
                .damage(damage)
                .target(abstractMonster)
                .addToBottom();

        new ApplyPowerBuilder()
                .target(abstractMonster)
                .power(FrostPower.class)
                .amount(magicNumber)
                .addToBottom();

        this.addToBottom(
                new MakeTempCardInDiscardAction(new VoidCard(), 1)
        );
    }

}
