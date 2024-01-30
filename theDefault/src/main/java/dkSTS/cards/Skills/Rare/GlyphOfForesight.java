package dkSTS.cards.Skills.Rare;

import com.megacrit.cardcrawl.actions.watcher.OmniscienceAction;
import com.megacrit.cardcrawl.cards.purple.Omniscience;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dkSTS.actions.ChooseForesightCardAction;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;

public class GlyphOfForesight extends AbstractBruxaCard {
    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(GlyphOfForesight.class)
            .img("Skill.png")
            .target(CardTarget.SELF)
            .rarity(CardRarity.RARE)
            .type(CardType.SKILL)
            .cost(1)
            .build();

    private static final int TIMES = 1;
    private static final int TIMES_UPGRADE = 1;

    public GlyphOfForesight() {
        super(data);

        baseMagicNumber = magicNumber = TIMES;
        exhaust = true;
    }

    @Override
    protected void UpgradeParameters() {
        upgradeMagicNumber(TIMES_UPGRADE);
        UpgradeDescription();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

        this.addToBot(
                new ChooseForesightCardAction(magicNumber)
        );
    }
}
