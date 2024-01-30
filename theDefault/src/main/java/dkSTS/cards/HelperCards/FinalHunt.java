package dkSTS.cards.HelperCards;

import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dkSTS.actions.DevourWolvesAction;
import dkSTS.actions.SicWolvesAction;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;
import dkSTS.stances.DireWolfStance;

public class FinalHunt extends AbstractBruxaCard {
    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(FinalHunt.class)
            .img("Attack.png")
            .type(CardType.ATTACK)
            .target(CardTarget.ALL)
            .color(CardColor.COLORLESS)
            .rarity(CardRarity.SPECIAL)
            .cost(2)
            .build();

    private static final int TIMES = 3;
    private static final int TIMES_UPGRADE = 1;

    public FinalHunt() {
        super(data);

        baseMagicNumber = magicNumber = TIMES;
        exhaust = true;
    }

    @Override
    protected void UpgradeParameters() {
        upgradeMagicNumber(TIMES_UPGRADE);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        for (int i = 0; i < magicNumber; ++i) {
            this.addToBottom(
                    new SicWolvesAction()
            );
        }

        this.addToBottom(
                new DevourWolvesAction()
        );

        this.addToBottom(
                new ChangeStanceAction(new DireWolfStance())
        );
    }
}
