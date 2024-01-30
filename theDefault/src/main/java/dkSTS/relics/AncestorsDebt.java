package dkSTS.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import dkSTS.relics.Helpers.RelicData;
import dkSTS.relics.Helpers.RelicDataBuilder;

public class AncestorsDebt extends AbstractCustomRelic {
    public static RelicData data = new RelicDataBuilder()
            .id(AncestorsDebt.class)
            .img_path("ancestors_debt.png")
            .tier(AbstractRelic.RelicTier.STARTER)
            .sfx(AbstractRelic.LandingSound.SOLID)
            .build();

    public AncestorsDebt() {
        super(data);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void onIncreaseMaxHealth(int amount) {
        if (!usedUp) {
            AbstractPlayer p = AbstractDungeon.player;

            p.loseGold(5*amount);
            this.flash();
        }
    }

    @Override
    public void onObtainCard(AbstractCard c) {
        if (!usedUp) {
            AbstractPlayer p = AbstractDungeon.player;

            int gmult = 0;
            switch (c.rarity) {
                case COMMON:
                    gmult = 5;
                    break;
                case UNCOMMON:
                    gmult = 10;
                    break;
                case RARE:
                    gmult = 15;
                    break;
                default:
                    gmult = 0;
                    break;
            }
            if (gmult > 0) {
                this.flash();
                p.loseGold(gmult);
            }

        }
    }
}
