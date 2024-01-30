package dkSTS.cards.Skills.Rare;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;

public class Stalk extends AbstractBruxaCard {
    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(Stalk.class)
            .img("Skill.png")
            .rarity(CardRarity.RARE)
            .type(CardType.SKILL)
            .target(CardTarget.NONE)
            .build();

    private static final int SCRY = 8;
    private static final int SCRY_UPGRADE = 2;

    private static final int DRAW = 2;
    private static final int DRAW_UPGRADE = 1;

    public Stalk() {
        super(data);

        isEthereal = true;
        exhaust = exhaustOnUseOnce = true;
        isInnate = true;

        baseMagicNumber = magicNumber = SCRY;
        defaultBaseSecondMagicNumber = defaultSecondMagicNumber = DRAW;
    }

    @Override
    protected void UpgradeParameters() {
        upgradeMagicNumber(SCRY_UPGRADE);
        upgradeDefaultSecondMagicNumber(DRAW_UPGRADE);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        this.addToBottom(
                new ScryAction(magicNumber)
        );
        this.addToBottom(
                new DrawCardAction(defaultBaseSecondMagicNumber)
        );
    }
}
