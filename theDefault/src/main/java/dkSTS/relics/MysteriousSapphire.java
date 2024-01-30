package dkSTS.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.colorless.Enlightenment;
import dkSTS.events.MysteriousCaller;
import dkSTS.relics.Helpers.RelicData;
import dkSTS.relics.Helpers.RelicDataBuilder;

public class MysteriousSapphire extends AbstractCustomRelic {
    public static RelicData data = new RelicDataBuilder()
            .id(MysteriousSapphire.class)
            .tier(RelicTier.SPECIAL)
            .img_path("sapphire.png")
            .sfx(LandingSound.MAGICAL)
            .build();

    public MysteriousSapphire() {
        super(data);

        counter = 1;
    }

    @Override
    public void onCardDraw(AbstractCard drawnCard) {
        super.onCardDraw(drawnCard);
        this.counter++;

        if (counter >= 40) {
            onMeetCondition(drawnCard);
            counter = 1;
        }
    }

    private void onMeetCondition(AbstractCard card) {
        card.costForTurn = 0;
        card.isCostModifiedForTurn = true;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
