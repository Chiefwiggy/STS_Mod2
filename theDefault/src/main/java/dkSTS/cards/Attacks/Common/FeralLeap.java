package dkSTS.cards.Attacks.Common;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.cards.Abstracts.AbstractWildCard;
import dkSTS.cards.Helpers.ApplyPowerBuilder;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;
import dkSTS.cards.Helpers.DamageActionBuilder;
import dkSTS.powers.BloodPower;

public class FeralLeap extends AbstractWildCard {

    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(FeralLeap.class)
            .img("FeralLeap.png")
            .target(CardTarget.ENEMY)
            .type(CardType.ATTACK)
            .rarity(CardRarity.COMMON)
            .build();

    private static final int BASE_DAMAGE = 7;
    private static final int DAMAGE_UPGRADE = 2;
    private static final int HUNGER_GAIN = 2;
    private static final int HUNGER_UPGRADE = 1;

    public FeralLeap() {
        super(data);

        baseDamage = BASE_DAMAGE;
        baseMagicNumber = magicNumber = HUNGER_GAIN;
    }

    @Override
    protected void UpgradeParameters() {
        upgradeMagicNumber(HUNGER_UPGRADE);
        upgradeDamage(DAMAGE_UPGRADE);
    }




    @Override
    public void wildUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

        new DamageActionBuilder()
                .damage(damage)
                .target(abstractMonster)
                .animation(AbstractGameAction.AttackEffect.SLASH_VERTICAL)
                .addToBottom();

        new ApplyPowerBuilder()
                .power(BloodPower.class)
                .amount(magicNumber)
                .addToBottom();
    }


}
