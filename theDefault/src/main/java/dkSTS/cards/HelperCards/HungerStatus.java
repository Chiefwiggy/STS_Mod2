package dkSTS.cards.HelperCards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.cards.Helpers.ApplyPowerBuilder;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;
import dkSTS.powers.BloodPower;

public class HungerStatus extends AbstractBruxaCard {

    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(HungerStatus.class)
            .img("Skill.png")
            .rarity(CardRarity.SPECIAL)
            .unplayable()
            .target(CardTarget.NONE)
            .color(CardColor.COLORLESS)
            .type(CardType.STATUS)
            .build();

    public HungerStatus() {
        super(data);

        baseMagicNumber = magicNumber = 1;
        isEthereal = true;
        exhaust = exhaustOnUseOnce = true;

    }

    @Override
    protected void UpgradeParameters() {
        upgradeMagicNumber(1);
    }


    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if (this.dontTriggerOnUseCard) {
            new ApplyPowerBuilder()
                    .power(BloodPower.class)
                    .amount(magicNumber)
                    .addToBottom();
        }
    }

    @Override
    public void triggerOnEndOfTurnForPlayingCard() {
        this.dontTriggerOnUseCard = true;
        AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(this, true));
    }
}
