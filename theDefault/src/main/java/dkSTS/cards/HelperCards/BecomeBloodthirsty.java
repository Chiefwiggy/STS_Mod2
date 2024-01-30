package dkSTS.cards.HelperCards;

import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;
import dkSTS.stances.BloodlustStance;
import dkSTS.stances.InvisibilityStance;

public class BecomeBloodthirsty extends AbstractBruxaCard {
    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(BecomeBloodthirsty.class)
            .img("Skill.png")
            .target(CardTarget.SELF)
            .type(CardType.SKILL)
            .rarity(CardRarity.SPECIAL)
            .color(CardColor.COLORLESS)
            .unplayable()
            .build();

    public BecomeBloodthirsty() {
        super(data);
    }

    @Override
    protected void UpgradeParameters() {

    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        onChoseThisOption();
    }

    @Override
    public void onChoseThisOption() {
        this.addToBot(
                new ChangeStanceAction(new BloodlustStance())
        );
    }
}
