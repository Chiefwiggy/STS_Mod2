package dkSTS.cards.HelperCards;

import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;

public class RedcapsVitality extends AbstractBruxaCard {
    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(RedcapsVitality.class)
            .type(AbstractCard.CardType.SKILL)
            .rarity(AbstractCard.CardRarity.SPECIAL)
            .color(AbstractCard.CardColor.COLORLESS)
            .cost(0)
            .img("Skill.png")
            .build();

    private static final int TEMP_HP_GAINED = 12;
    private static final int UPGRADE = 6;

    public RedcapsVitality() {
        super(data);

        baseMagicNumber = magicNumber = TEMP_HP_GAINED;
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
        addToBottom(
                new AddTemporaryHPAction(p,p,magicNumber)
        );
    }
}
