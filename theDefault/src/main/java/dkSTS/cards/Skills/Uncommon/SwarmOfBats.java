package dkSTS.cards.Skills.Uncommon;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.cards.Helpers.ApplyPowerBuilder;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;
import dkSTS.powers.BloodPower;

import java.util.Iterator;

public class SwarmOfBats extends AbstractBruxaCard {

    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(SwarmOfBats.class)
            .img("SwarmOfBats.png")
            .rarity(CardRarity.UNCOMMON)
            .target(CardTarget.ALL_ENEMY)
            .type(CardType.SKILL)
            .cost(2)
            .build();

    private static final int WEAK = 3;

    public SwarmOfBats() {
        super(data);

        baseMagicNumber = magicNumber = WEAK;
    }

    @Override
    protected void UpgradeParameters() {
        upgradeBaseCost(1);

        baseMagicNumber = magicNumber = WEAK;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

        int count = 0;
        for (AbstractMonster mon : AbstractDungeon.getMonsters().monsters) {
            ++count;
        }

        new ApplyPowerBuilder()
                .power(WeakPower.class)
                .targetAll()
                .amount(magicNumber)
                .addToBottom();

        new ApplyPowerBuilder()
                .power(BloodPower.class)
                .amount(count)
                .addToBottom();
    }
}
