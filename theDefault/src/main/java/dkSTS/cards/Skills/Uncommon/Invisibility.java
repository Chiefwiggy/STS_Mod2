package dkSTS.cards.Skills.Uncommon;

import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;
import dkSTS.stances.AbstractBruxaStance;
import dkSTS.stances.InvisibilityStance;

public class Invisibility extends AbstractBruxaCard {
    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(Invisibility.class)
            .img("Invisibility.png")
            .type(CardType.SKILL)
            .rarity(CardRarity.UNCOMMON)
            .cost(2)
            .target(CardTarget.SELF)
            .build();

    public Invisibility() {
        super(data);

        exhaust = true;
        isInnate = true;
    }

    @Override
    protected void UpgradeParameters() {
        UpgradeDescription();
        selfRetain = true;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        this.addToBottom(
                new ChangeStanceAction(new InvisibilityStance())
        );
    }
}
