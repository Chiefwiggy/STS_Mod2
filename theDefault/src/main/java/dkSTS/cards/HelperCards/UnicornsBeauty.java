package dkSTS.cards.HelperCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BerserkPower;
import com.megacrit.cardcrawl.powers.BufferPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.cards.Helpers.ApplyPowerBuilder;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;

public class UnicornsBeauty extends AbstractBruxaCard {
    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(UnicornsBeauty.class)
            .type(CardType.SKILL)
            .rarity(CardRarity.SPECIAL)
            .color(CardColor.COLORLESS)
            .cost(0)
            .img("Skill.png")
            .build();

    private static final int BUFFER_GAIN = 1;
    private static final int UPGRADE = 1;

    public UnicornsBeauty() {
        super(data);

        baseMagicNumber = magicNumber =  BUFFER_GAIN;
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
                .power(BufferPower.class)
                .amount(magicNumber)
                .addToBottom();
    }
}
