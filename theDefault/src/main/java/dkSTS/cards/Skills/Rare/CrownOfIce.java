package dkSTS.cards.Skills.Rare;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ThornsPower;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.cards.Helpers.ApplyPowerBuilder;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;
import dkSTS.powers.FrostPower;
import dkSTS.powers.WiltingThornsPower;

public class CrownOfIce extends AbstractBruxaCard {

    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(CrownOfIce.class)
            .img("Skill.png")
            .rarity(CardRarity.RARE)
            .type(CardType.SKILL)
            .cost(1)
            .target(CardTarget.SELF)
            .build();

    private static final int BLOCK = 6;
    private static final int THORNS = 6;

    public CrownOfIce() {
        super(data);

        baseBlock = BLOCK;
        baseMagicNumber = magicNumber = THORNS;
        exhaust = exhaustOnUseOnce = true;
    }

    @Override
    protected void UpgradeParameters() {
        UpgradeDescription();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBottom(
                new GainBlockAction(p, block)
        );
        if (!upgraded) {
            new ApplyPowerBuilder()
                    .power(FrostPower.class)
                    .amount(1)
                    .addToBottom();
        }

        new ApplyPowerBuilder()
                .power(ThornsPower.class)
                .amount(magicNumber)
                .addToBottom();

        new ApplyPowerBuilder()
                .power(WiltingThornsPower.class)
                .amount(magicNumber)
                .addToBottom();
    }
}
