package dkSTS.cards.HelperCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BerserkPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.cards.Helpers.ApplyPowerBuilder;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;

public class TitansStrength extends AbstractBruxaCard {

    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(TitansStrength.class)
            .type(CardType.SKILL)
            .rarity(CardRarity.SPECIAL)
            .color(CardColor.COLORLESS)
            .unplayable()
            .img("Skill.png")
            .build();

    public TitansStrength() {
        super(data);

        baseMagicNumber = magicNumber = STRENGTH_GAIN;
    }

    private static final int STRENGTH_GAIN = 2;
    private static final int UPGRADE = 1;

    @Override
    protected void UpgradeParameters() {
        upgradeMagicNumber(UPGRADE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        onChoseThisOption();
    }

    @Override
    public void onChoseThisOption() {
        new ApplyPowerBuilder()
                .power(StrengthPower.class)
                .amount(magicNumber)
                .addToBottom();
    }
}
