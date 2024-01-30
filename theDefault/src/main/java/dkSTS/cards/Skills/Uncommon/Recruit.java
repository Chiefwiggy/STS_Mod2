package dkSTS.cards.Skills.Uncommon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.cards.Helpers.ApplyPowerBuilder;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;
import dkSTS.powers.WolfPower;

public class Recruit extends AbstractBruxaCard {
    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(Recruit.class)
            .img("Recruit.png")
            .rarity(CardRarity.UNCOMMON)
            .target(CardTarget.NONE)
            .type(CardType.SKILL)
            .cost(1)
            .build();

    private static final int WOLVES_GAINED = 3;
    private static final int WOLVES_GAINED_UPGRADE = 2;
    public Recruit() {
        super(data);

        exhaust = exhaustOnUseOnce = true;
        baseMagicNumber = magicNumber = WOLVES_GAINED;
    }

    @Override
    protected void UpgradeParameters() {
        upgradeMagicNumber(WOLVES_GAINED_UPGRADE);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        new ApplyPowerBuilder()
                .power(WolfPower.class)
                .amount(magicNumber)
                .addToBottom();
    }
}
