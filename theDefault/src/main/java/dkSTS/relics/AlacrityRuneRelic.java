package dkSTS.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import dkSTS.powers.EvasionPower;
import dkSTS.relics.Helpers.RelicData;
import dkSTS.relics.Helpers.RelicDataBuilder;

public class AlacrityRuneRelic extends AbstractCustomRelic {

    public static RelicData data = new RelicDataBuilder()
            .id(AlacrityRuneRelic.class)
            .img_path("alacrity.png")
            .tier(RelicTier.STARTER)
            .sfx(LandingSound.MAGICAL)
            .build();

    public static int battlesWon;
    public AlacrityRuneRelic() {
        super(data);
        battlesWon = 5;
    }

    @Override
    public void atBattleStart() {
        AbstractPlayer p = AbstractDungeon.player;
        battlesWon++;

        addToBot(
                new ApplyPowerAction(p, p, new EvasionPower(p, p, 1), 1)
        );
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
