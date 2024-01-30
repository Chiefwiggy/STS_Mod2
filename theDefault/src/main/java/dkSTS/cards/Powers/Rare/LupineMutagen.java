package dkSTS.cards.Powers.Rare;

import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.cards.Helpers.ApplyPowerBuilder;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;
import dkSTS.powers.LupineMutagenPower;
import dkSTS.stances.DireWolfStance;

public class LupineMutagen extends AbstractBruxaCard {
    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(LupineMutagen.class)
            .img("LupineMutagen.png")
            .target(CardTarget.SELF)
            .type(CardType.POWER)
            .rarity(CardRarity.RARE)
            .cost(1)
            .build();


    public LupineMutagen() {
        super(data);

    }

    @Override
    protected void UpgradeParameters() {
        upgradeBaseCost(0);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBottom(new ChangeStanceAction(new DireWolfStance()));

        new ApplyPowerBuilder()
                .power(LupineMutagenPower.class)
                .amount(1)
                .addToBottom();
    }
}
