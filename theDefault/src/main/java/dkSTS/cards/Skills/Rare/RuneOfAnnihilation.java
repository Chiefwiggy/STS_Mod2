package dkSTS.cards.Skills.Rare;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BerserkPower;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.cards.Helpers.ApplyPowerBuilder;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;
import dkSTS.powers.AnnihilationRunePower;

public class RuneOfAnnihilation extends AbstractBruxaCard {

    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(RuneOfAnnihilation.class)
            .img("HexDisintegration.png")
            .rarity(AbstractCard.CardRarity.RARE)
            .target(AbstractCard.CardTarget.ENEMY)
            .type(AbstractCard.CardType.SKILL)
            .cost(3)
            .build();

    private final static int ANNIHILATION = 46;
    private final static int ANNIHILATION_UPGRADE = 12;

    public RuneOfAnnihilation() {
        super(data);

        baseMagicNumber = magicNumber = ANNIHILATION;
        exhaust = exhaustOnUseOnce = true;
    }

    @Override
    protected void UpgradeParameters() {
        upgradeMagicNumber(ANNIHILATION_UPGRADE);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        new ApplyPowerBuilder()
                .power(AnnihilationRunePower.class)
                .amount(magicNumber)
                .target(abstractMonster)
                .addToBottom();
    }
}
