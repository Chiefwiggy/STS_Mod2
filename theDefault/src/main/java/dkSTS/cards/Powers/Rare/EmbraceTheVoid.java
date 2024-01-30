package dkSTS.cards.Powers.Rare;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.cards.Helpers.ApplyPowerBuilder;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;
import dkSTS.powers.EmbraceTheVoidPower;

public class EmbraceTheVoid extends AbstractBruxaCard {
    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(EmbraceTheVoid.class)
            .img("Power.png")
            .type(CardType.POWER)
            .target(CardTarget.SELF)
            .rarity(CardRarity.RARE)
            .cost(1)
            .build();

    public EmbraceTheVoid() {
        super(data);

        isInnate = true;
    }

    @Override
    protected void UpgradeParameters() {
        upgradeBaseCost(0);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        new ApplyPowerBuilder()
                .power(EmbraceTheVoidPower.class)
                .amount(-1)
                .addToBottom();
    }
}
