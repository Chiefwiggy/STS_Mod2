package dkSTS.cards.Attacks.Rare;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.cards.HelperCards.SecondHunt;
import dkSTS.cards.Helpers.ApplyPowerBuilder;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;
import dkSTS.cards.Helpers.DamageActionBuilder;
import dkSTS.powers.FeralPower;
import dkSTS.powers.WolfPower;

public class FirstHunt extends AbstractBruxaCard {
    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(FirstHunt.class)
            .img("Attack.png")
            .type(CardType.ATTACK)
            .rarity(CardRarity.RARE)
            .target(CardTarget.ENEMY)
            .cost(2)
            .build();

    private static final int DAMAGE = 7;
    private static final int DAMAGE_UPGRADE = 3;

    private static final int WOLF_GAIN = 1;
    private static final int WOLF_UPGRADE = 1;

    public FirstHunt() {
        super(data);

        damage = baseDamage = DAMAGE;
        exhaust = true;

        baseMagicNumber = magicNumber = WOLF_GAIN;

        cardsToPreview = new SecondHunt();
    }

    @Override
    protected void UpgradeParameters() {
        upgradeDamage(DAMAGE_UPGRADE);
        upgradeMagicNumber(WOLF_UPGRADE);
        UpgradeDescription();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster m) {

        new DamageActionBuilder()
                .damage(damage)
                .target(m)
                .addToBottom();

        new ApplyPowerBuilder()
                .power(WolfPower.class)
                .amount(magicNumber)
                .addToBottom();

        SecondHunt sh = new SecondHunt();
        if (upgraded) {
            sh.upgrade();
        }
        this.addToBottom(
                new MakeTempCardInDiscardAction(sh, 1)
        );
    }
}
