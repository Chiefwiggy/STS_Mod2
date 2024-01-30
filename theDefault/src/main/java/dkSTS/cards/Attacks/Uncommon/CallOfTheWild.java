package dkSTS.cards.Attacks.Uncommon;

import com.megacrit.cardcrawl.actions.common.ReduceCostAction;
import com.megacrit.cardcrawl.cards.blue.Streamline;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dkSTS.actions.SicWolvesAction;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.cards.Helpers.ApplyPowerBuilder;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;
import dkSTS.powers.WolfPower;

public class CallOfTheWild extends AbstractBruxaCard {
    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(CallOfTheWild.class)
            .img("CallOfTheWild.png")
            .rarity(CardRarity.UNCOMMON)
            .type(CardType.ATTACK)
            .cost(2)
            .target(CardTarget.SELF)
            .build();

    private static final int WOLVES = 1;
    private static final int WOLF_UPGRADE = 1;

    public CallOfTheWild() {
        super(data);

        baseMagicNumber = magicNumber = WOLVES;
    }

    @Override
    protected void UpgradeParameters() {
        upgradeMagicNumber(WOLF_UPGRADE);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        new ApplyPowerBuilder()
                .power(WolfPower.class)
                .amount(magicNumber)
                .addToBottom();

        this.addToBottom(
                new SicWolvesAction()
        );
        this.addToBottom(
                new SicWolvesAction()
        );

        this.addToBottom(
                new ReduceCostAction(this.uuid, 1)
        );
    }
}
