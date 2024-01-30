package dkSTS.cards.HelperCards;

import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.cards.Helpers.ApplyPowerBuilder;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;
import dkSTS.powers.VitalityPower;

public class RedcapsVitality extends AbstractBruxaCard {
    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(RedcapsVitality.class)
            .type(AbstractCard.CardType.SKILL)
            .rarity(AbstractCard.CardRarity.SPECIAL)
            .color(AbstractCard.CardColor.COLORLESS)
            .unplayable()
            .img("Skill.png")
            .build();

    private static final int VIT_GAINED = 1;
    private static final int UPGRADE = 1;

    public RedcapsVitality() {
        super(data);

        baseMagicNumber = magicNumber = VIT_GAINED;
    }



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
                .power(VitalityPower.class)
                .amount(1)
                .addToBottom();
    }
}
