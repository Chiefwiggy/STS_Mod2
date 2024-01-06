package dkSTS.cards.HelperCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BerserkPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.cards.Helpers.ApplyPowerBuilder;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;

public class QuicklingsSpeed extends AbstractBruxaCard  {
    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(QuicklingsSpeed.class)
            .type(AbstractCard.CardType.SKILL)
            .rarity(AbstractCard.CardRarity.SPECIAL)
            .color(AbstractCard.CardColor.COLORLESS)
            .cost(0)
            .img("Skill.png")
            .build();

    private static final int DEX_GAIN = 2;
    private static final int UPGRADE = 1;

    public QuicklingsSpeed() {
        super(data);

        baseMagicNumber = magicNumber = DEX_GAIN;
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
        AbstractPlayer p = AbstractDungeon.player;
        new ApplyPowerBuilder()
                .power(DexterityPower.class)
                .amount(magicNumber)
                .addToBottom();
    }
}
