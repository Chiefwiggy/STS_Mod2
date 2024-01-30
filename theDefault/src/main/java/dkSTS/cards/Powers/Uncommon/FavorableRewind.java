package dkSTS.cards.Powers.Uncommon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.cards.Helpers.ApplyPowerBuilder;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;
import dkSTS.powers.RewindPower;

public class FavorableRewind extends AbstractBruxaCard {
    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(FavorableRewind.class)
            .img("FavorableRewind.png")
            .type(CardType.POWER)
            .rarity(CardRarity.UNCOMMON)
            .target(CardTarget.NONE)
            .cost(1)
            .build();

    public FavorableRewind() {
        super(data);
    }

    @Override
    protected void UpgradeParameters() {
        isInnate = true;
        UpgradeDescription();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        new ApplyPowerBuilder()
                .power(RewindPower.class)
                .addToBottom();
    }

}
