package dkSTS.cards.Skills.Rare;

import com.megacrit.cardcrawl.cards.red.SearingBlow;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dkSTS.cards.Abstracts.AbstractVoidfluxCard;
import dkSTS.cards.Helpers.ApplyPowerBuilder;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;
import dkSTS.powers.NullRunePower;

public class RuneOfNull extends AbstractVoidfluxCard {
    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(RuneOfNull.class)
            .img("RuneOfNull.png")
            .target(CardTarget.ENEMY)
            .rarity(CardRarity.RARE)
            .type(CardType.SKILL)
            .cost(1)
            .build();

    public RuneOfNull() {
        super(data);

        exhaust = exhaustOnUseOnce = true;
        isEthereal = true;
    }

    @Override
    protected void UpgradeParameters() {
        isEthereal = false;
        UpgradeDescription();
    }

    @Override
    public void changeOnVoid() {
        target = CardTarget.ALL_ENEMY;
    }

    @Override
    public void changeOnNotVoid() {
        target = CardTarget.ENEMY;
    }

    @Override
    public void useAlways(AbstractPlayer p, AbstractMonster m) {
        new ApplyPowerBuilder()
                .target(m)
                .power(NullRunePower.class)
                .addToBottom();
    }

    @Override
    public void useOnFlux(AbstractPlayer p, AbstractMonster m) {
        new ApplyPowerBuilder()
                .targetAll()
                .power(NullRunePower.class)
                .addToBottom();
    }

}
