package dkSTS.cards.Skills.Uncommon;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.cards.Helpers.ApplyPowerBuilder;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;
import dkSTS.powers.BadDreamPower;

public class DreamEater extends AbstractBruxaCard {
    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(DreamEater.class)
            .img("DreamEater.png")
            .type(CardType.SKILL)
            .rarity(CardRarity.UNCOMMON)
            .target(CardTarget.ENEMY)
            .cost(1)
            .build();

    private static final int DAMAGE = 3;
    private static final int DAMAGE_UPGRADE = 1;

    public DreamEater() {
        super(data);

        baseMagicNumber = magicNumber = DAMAGE;
        exhaust = true;
    }

    @Override
    protected void UpgradeParameters() {
        upgradeMagicNumber(DAMAGE_UPGRADE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        new ApplyPowerBuilder()
                .power(BadDreamPower.class)
                .target(m)
                .amount(magicNumber)
                .addToBottom();
    }
}
