package dkSTS.cards.Attacks.Common;

import com.megacrit.cardcrawl.cards.colorless.DarkShackles;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import dkSTS.cards.Abstracts.AbstractFluxCard;
import dkSTS.cards.Abstracts.AbstractVoidfluxCard;
import dkSTS.cards.Abstracts.AbstractWildVoidfluxCard;
import dkSTS.cards.Helpers.ApplyPowerBuilder;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;
import dkSTS.cards.Helpers.DamageActionBuilder;

public class Shillelagh extends AbstractFluxCard {
    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(Shillelagh.class)
            .img("Shillelagh.png")
            .target(CardTarget.ENEMY)
            .type(CardType.ATTACK)
            .rarity(CardRarity.COMMON)
            .cost(1)
            .build();

    private static final int DAMAGE = 7;
    private static final int DAMAGE_UPGRADE = 4;

    private static final int LOSE_STR = 7;
    private static final int LOSE_STR_UPGRADE = 4;

    public Shillelagh() {
        super(data);

        baseDamage = damage = DAMAGE;
        baseMagicNumber = magicNumber = LOSE_STR;
    }

    @Override
    protected void UpgradeParameters() {
        upgradeDamage(DAMAGE_UPGRADE);
        upgradeMagicNumber(LOSE_STR_UPGRADE);
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
        new ApplyPowerBuilder()
                .power(StrengthPower.class)
                .amount(-magicNumber)
                .target(m)
                .addToBottom();
        if (m != null && !m.hasPower(ArtifactPower.POWER_ID)) {
            new ApplyPowerBuilder()
                    .power(GainStrengthPower.class)
                    .amount(magicNumber)
                    .target(m)
                    .addToBottom();
        }
    }
}
