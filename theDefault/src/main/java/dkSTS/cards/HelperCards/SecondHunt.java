package dkSTS.cards.HelperCards;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dkSTS.actions.SicWolvesAction;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;
import dkSTS.cards.Helpers.DamageActionBuilder;

public class SecondHunt extends AbstractBruxaCard {
    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(SecondHunt.class)
            .img("Attack.png")
            .rarity(CardRarity.SPECIAL)
            .type(CardType.ATTACK)
            .cost(2)
            .color(CardColor.COLORLESS)
            .target(CardTarget.ALL_ENEMY)
            .build();

    private static final int DAMAGE = 11;
    private static final int DAMAGE_UPGRADE = 3;

    public SecondHunt() {
        super(data);

        exhaust = true;
        damage = baseDamage = DAMAGE;

        cardsToPreview = new FinalHunt();
    }

    @Override
    protected void UpgradeParameters() {
        upgradeDamage(DAMAGE_UPGRADE);
        UpgradeDescription();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

        new DamageActionBuilder()
                .targetAll(multiDamage)
                .damage(damage)
                .addToBottom();

        this.addToBottom(
                new SicWolvesAction()
        );

        FinalHunt sh = new FinalHunt();
        if (upgraded) {
            sh.upgrade();
        }
        this.addToBottom(
                new MakeTempCardInDiscardAction(sh, 1)
        );
    }
}
