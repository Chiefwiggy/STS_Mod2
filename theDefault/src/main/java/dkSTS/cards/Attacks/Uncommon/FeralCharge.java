package dkSTS.cards.Attacks.Uncommon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dkSTS.actions.DevourWolvesAction;
import dkSTS.actions.SicWolvesAction;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.cards.Abstracts.AbstractWildCard;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;
import dkSTS.cards.Helpers.DamageActionBuilder;

public class FeralCharge extends AbstractWildCard {
    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(FeralCharge.class)
            .img("FeralCharge.png")
            .type(CardType.ATTACK)
            .rarity(CardRarity.UNCOMMON)
            .cost(2)
            .target(CardTarget.ALL_ENEMY)
            .build();

    private static final int DAMAGE = 8;
    private static final int DAMAGE_UPGRADE = 3;

    public FeralCharge() {
        super(data);

        baseDamage = damage = DAMAGE;
        isMultiDamage = true;
        exhaust = true;
    }

    @Override
    protected void UpgradeParameters() {
        upgradeDamage(DAMAGE_UPGRADE);
    }

    @Override
    public void wildUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        this.addToBottom(
                new SicWolvesAction()
        );
        this.addToBottom(
                new DevourWolvesAction()
        );
        new DamageActionBuilder()
                .targetAll(multiDamage)
                .damage(damage)
                .addToBottom();

    }
}
