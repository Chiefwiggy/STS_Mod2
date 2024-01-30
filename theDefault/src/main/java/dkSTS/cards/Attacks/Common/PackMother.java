package dkSTS.cards.Attacks.Common;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dkSTS.actions.SicWolvesAction;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.cards.Helpers.ApplyPowerBuilder;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;
import dkSTS.cards.Helpers.DamageActionBuilder;
import dkSTS.powers.WolfPower;

public class PackMother extends AbstractBruxaCard {
    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(PackMother.class)
            .img("PackMother.png")
            .target(CardTarget.NONE)
            .rarity(CardRarity.COMMON)
            .type(CardType.ATTACK)
            .cost(2)
            .build();

    private final static int WOLVES = 2;
    private final static int WOLVES_UPGRADE = 1;

    private final static int DAMAGE = 7;
    private final static int DAMAGE_UPGRADE = 2;

    public PackMother() {
        super(data);

        baseMagicNumber = magicNumber = WOLVES;
        baseDamage = damage = DAMAGE;

        isMultiDamage = true;
    }


    @Override
    protected void UpgradeParameters() {
        upgradeDamage(DAMAGE_UPGRADE);
        upgradeMagicNumber(WOLVES_UPGRADE);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

        new DamageActionBuilder()
                .damage(damage)
                .targetAll(multiDamage)
                .addToBottom();

        new ApplyPowerBuilder()
                .power(WolfPower.class)
                .amount(magicNumber)
                .addToBottom();


        this.addToBottom(
                new SicWolvesAction()
        );
    }
}
