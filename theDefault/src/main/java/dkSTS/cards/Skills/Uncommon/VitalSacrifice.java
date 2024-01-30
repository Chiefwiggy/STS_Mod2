package dkSTS.cards.Skills.Uncommon;

import com.evacipated.cardcrawl.mod.stslib.patches.cardInterfaces.BranchingUpgradesPatch;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.colorless.DarkShackles;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.cards.Helpers.ApplyPowerBuilder;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;
import dkSTS.powers.VitalityPower;

public class VitalSacrifice extends AbstractBruxaCard {
    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(VitalSacrifice.class)
            .img("Skill.png")
            .rarity(CardRarity.UNCOMMON)
            .target(CardTarget.SELF)
            .type(CardType.SKILL)
            .cost(2)
            .build();

    private static final int STRENGTH_LOSS = 2;
    private static final int STRENGTH_LOSS_UPGRADE = -1;

    private static final int VIGOR_GAIN = 16;

    private static final int VITALITY_GAIN = 1;


    public VitalSacrifice() {
        super(data);

        baseMagicNumber = magicNumber = VIGOR_GAIN;
        __baseMagicThird = __magicThird = STRENGTH_LOSS;
        defaultBaseSecondMagicNumber = defaultSecondMagicNumber = VITALITY_GAIN;

        exhaust = true;
    }

    @Override
    protected void UpgradeParameters() {
        upgradeMagicThird(STRENGTH_LOSS_UPGRADE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        new ApplyPowerBuilder()
                .power(StrengthPower.class)
                .amount(-__magicThird)
                .addToBottom();

        new ApplyPowerBuilder()
                .power(VigorPower.class)
                .amount(magicNumber)
                .addToBottom();

        new ApplyPowerBuilder()
                .power(VitalityPower.class)
                .amount(defaultSecondMagicNumber)
                .addToBottom();
    }
}
