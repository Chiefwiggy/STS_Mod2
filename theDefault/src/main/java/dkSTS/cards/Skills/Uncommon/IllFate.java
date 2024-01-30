package dkSTS.cards.Skills.Uncommon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dkSTS.cards.Abstracts.AbstractVoidfluxCard;
import dkSTS.cards.Helpers.ApplyPowerBuilder;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;
import dkSTS.powers.ExposePower;

public class IllFate extends AbstractVoidfluxCard {
    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(IllFate.class)
            .img("IllFate.png")
            .rarity(CardRarity.UNCOMMON)
            .type(CardType.SKILL)
            .target(CardTarget.ENEMY)
            .cost(1)
            .build();


    private static final int EXPOSE = 2;
    private static final int EXPOSE_UPGRADE = 1;

    private static final int EX_VD = 3;
    private static final int EX_VD_UP = 1;

    public IllFate() {
        super(data);

        magicNumber = baseMagicNumber = EXPOSE;
        __baseMagicThird = __magicThird = EX_VD;

        exhaust = true;
    }

    @Override
    protected void UpgradeParameters() {
        upgradeMagicNumber(EXPOSE_UPGRADE);
        upgradeMagicThird(EX_VD_UP);


    }

    @Override
    public void useAlways(AbstractPlayer p, AbstractMonster m) {
        new ApplyPowerBuilder()
                .power(ExposePower.class)
                .amount(magicNumber)
                .target(m)
                .addToBottom();
    }

    @Override
    public void useOnFlux(AbstractPlayer p, AbstractMonster m) {
        new ApplyPowerBuilder()
                .power(ExposePower.class)
                .amount(__magicThird)
                .target(m)
                .addToBottom();
    }
}
