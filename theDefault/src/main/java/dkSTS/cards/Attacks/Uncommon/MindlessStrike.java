package dkSTS.cards.Attacks.Uncommon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dkSTS.actions.ExhaustCardFromDiscardPileAction;
import dkSTS.cards.Abstracts.AbstractWildCard;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;
import dkSTS.cards.Helpers.DamageActionBuilder;

public class MindlessStrike extends AbstractWildCard {
    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(MindlessStrike.class)
            .img("MindlessAttack.png")
            .type(CardType.ATTACK)
            .rarity(CardRarity.UNCOMMON)
            .target(CardTarget.ENEMY)
            .cost(1)
            .build();

    private static final int DAMAGE = 10;
    private static final int DAMAGE_UPGRADE = 6;

    private static final int EXHAUST = 1;
    private static final int EXHAUST_UPRGADE = 1;

    public MindlessStrike() {
        super(data);

        baseDamage = damage = DAMAGE;
        baseMagicNumber = magicNumber = EXHAUST;

        this.tags.add(CardTags.STRIKE);
    }

    @Override
    protected void UpgradeParameters() {
        upgradeDamage(DAMAGE_UPGRADE);
        upgradeMagicNumber(EXHAUST_UPRGADE);
        UpgradeDescription();
    }

    @Override
    public void wildUse(AbstractPlayer p, AbstractMonster m) {
        new DamageActionBuilder()
                .damage(damage)
                .target(m)
                .addToBottom();

        this.addToBottom(
                new ExhaustCardFromDiscardPileAction(magicNumber, false, false)
        );
    }
}
