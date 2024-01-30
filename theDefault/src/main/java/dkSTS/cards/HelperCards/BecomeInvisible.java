package dkSTS.cards.HelperCards;

import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;
import dkSTS.stances.InvisibilityStance;

public class BecomeInvisible extends AbstractBruxaCard {
    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(BecomeInvisible.class)
            .rarity(CardRarity.SPECIAL)
            .type(CardType.SKILL)
            .unplayable()
            .img("Skill.png")
            .color(CardColor.COLORLESS)
            .target(CardTarget.SELF)
            .build();

    public BecomeInvisible() {
        super(data);

        exhaust = exhaustOnUseOnce = true;
    }


    @Override
    protected void UpgradeParameters() {
        //nothing
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        onChoseThisOption();
    }

    @Override
    public void onChoseThisOption() {
        this.addToBot(
                new ChangeStanceAction(new InvisibilityStance())
        );
    }
}
