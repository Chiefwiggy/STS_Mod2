package dkSTS.cards.HelperCards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.cards.Abstracts.AbstractVoidfluxCard;
import dkSTS.cards.Helpers.ApplyPowerBuilder;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;
import dkSTS.cards.Helpers.DamageActionBuilder;
import dkSTS.powers.ExposePower;

public class EchoTendril extends AbstractVoidfluxCard {
    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(EchoTendril.class)
            .type(CardType.ATTACK)
            .rarity(CardRarity.SPECIAL)
            .target(CardTarget.ENEMY)
            .img("Attack.png")
            .color(CardColor.COLORLESS)
            .cost(0)
            .build();

    private static final int DAMAGE = 5;
    private static final int DAMAGE_UPGRADE = 2;

    private static final int EXP = 1;
    private static final int EXP_UP = 1;

    public EchoTendril() {
        super(data);

        baseDamage = damage = DAMAGE;
        baseMagicNumber = magicNumber = EXP;

        exhaust = exhaustOnUseOnce = true;
        isEthereal = true;
    }

    @Override
    protected void UpgradeParameters() {
        upgradeDamage(DAMAGE_UPGRADE);
        upgradeMagicNumber(EXP_UP);
    }

    @Override
    public void useAlways(AbstractPlayer p, AbstractMonster m) {
        new DamageActionBuilder()
                .damage(damage)
                .target(m)
                .addToBottom();
    }

    @Override
    public void useOnFlux(AbstractPlayer p, AbstractMonster m) {
        new ApplyPowerBuilder()
                .power(ExposePower.class)
                .amount(magicNumber)
                .target(m)
                .addToBottom();
    }
}
