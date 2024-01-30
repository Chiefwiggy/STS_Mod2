package dkSTS.cards.Attacks.Common;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dkSTS.actions.RedrawCardAction;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;
import dkSTS.cards.Helpers.DamageActionBuilder;

public class VineWhip extends AbstractBruxaCard {
    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(VineWhip.class)
            .img("VineWhip.png")
            .rarity(CardRarity.COMMON)
            .type(CardType.ATTACK)
            .target(CardTarget.ENEMY)
            .cost(1)
            .build();

    private static final int DAMAGE = 7;
    private static final int DAMAGE_UPGRADE = 3;

    private static final int REDRAW = 1;
    private static final int REDRAW_UPGRADE = 1;

    public VineWhip() {
        super(data);

        baseDamage = damage = DAMAGE;
        baseMagicNumber = magicNumber = REDRAW;
    }

    @Override
    protected void UpgradeParameters() {
        upgradeDamage(DAMAGE_UPGRADE);
        upgradeMagicNumber(REDRAW_UPGRADE);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        new DamageActionBuilder()
                .damage(damage)
                .target(abstractMonster)
                .addToBottom();

        this.addToBottom(
                new RedrawCardAction(abstractPlayer, magicNumber)
        );
    }
}
