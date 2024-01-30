package dkSTS.cards.Skills.Uncommon;

import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;
import dkSTS.cards.Helpers.DamageActionBuilder;

public class LifeTransference extends AbstractBruxaCard {
    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(LifeTransference.class)
            .img("LifeTransference.png")
            .rarity(CardRarity.UNCOMMON)
            .type(CardType.SKILL)
            .target(CardTarget.SELF)
            .cost(1)
            .build();

    private static final int LOSS = 8;
    private static final int LOSS_UPGRADE = -2;

    private static final int WARD = 15;

    public LifeTransference() {
        super(data);

        exhaust = true;
        baseMagicNumber = magicNumber = LOSS;
        __magicThird = __baseMagicThird = WARD;
    }

    @Override
    protected void UpgradeParameters() {
        upgradeMagicNumber(LOSS_UPGRADE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        new DamageActionBuilder()
                .target(p)
                .damageType(DamageInfo.DamageType.HP_LOSS)
                .damage(magicNumber)
                .addToBottom();

        this.addToBottom(
                new AddTemporaryHPAction(p, p, __magicThird)
        );
    }
}
