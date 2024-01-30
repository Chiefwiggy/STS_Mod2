package dkSTS.cards.Skills.Uncommon;

import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.actions.common.PlayTopCardAction;
import com.megacrit.cardcrawl.actions.common.ShuffleAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.colorless.DeepBreath;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.potions.DistilledChaosPotion;
import dkSTS.actions.PlayRandomCardAction;
import dkSTS.actions.RedrawCardAction;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.cards.Helpers.ApplyPowerBuilder;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;
import dkSTS.cards.Helpers.DamageActionBuilder;
import dkSTS.powers.BloodPower;

public class MemoryOfBlood extends AbstractBruxaCard {
    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(MemoryOfBlood.class)
            .img("MemoryOfBlood.png")
            .rarity(CardRarity.UNCOMMON)
            .type(CardType.SKILL)
            .cost(1)
            .target(CardTarget.NONE)
            .build();

    private static final int HUNGER = 3;
    private static final int HUNGER_UPGRADE = 1;

    private static final int HP_LOSS = 4;

    private static final int R_CARDS_PLAYED = 1;
    private static final int R_CARDS_PLAYED_UPGRADE = 1;

    public MemoryOfBlood() {
        super(data);
        baseMagicNumber = magicNumber = HUNGER;
        defaultBaseSecondMagicNumber = defaultSecondMagicNumber = HP_LOSS;
        __baseMagicThird = __magicThird = R_CARDS_PLAYED;
    }

    @Override
    protected void UpgradeParameters() {
        upgradeMagicNumber(HUNGER_UPGRADE);
        upgradeMagicThird(R_CARDS_PLAYED_UPGRADE);
        UpgradeDescription();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        new DamageActionBuilder()
                .damage(defaultSecondMagicNumber)
                .target(p)
                .damageType(DamageInfo.DamageType.HP_LOSS)
                .addToBottom();

        new ApplyPowerBuilder()
                .power(BloodPower.class)
                .amount(magicNumber)
                .addToBottom();


        this.addToBottom(
                new PlayRandomCardAction(__magicThird, p.discardPile, CardType.ATTACK)
        );
    }
}
