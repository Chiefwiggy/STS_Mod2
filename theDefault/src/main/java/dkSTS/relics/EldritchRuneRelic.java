package dkSTS.relics;

import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.PoisonPower;
import dkSTS.relics.Helpers.RelicData;
import dkSTS.relics.Helpers.RelicDataBuilder;

public class EldritchRuneRelic extends AbstractCustomRelic {

    public static RelicData data = new RelicDataBuilder()
            .id(EldritchRuneRelic.class)
            .path("placeholder_relic.png")
            .tier(RelicTier.COMMON)
            .sfx(LandingSound.MAGICAL)
            .build();

    public EldritchRuneRelic() {
        super(data);
    }

    private static int TEMP_HP = 5;

    @Override
    public void atBattleStart() {
        AbstractPlayer p = AbstractDungeon.player;

        addToBot(
                new AddTemporaryHPAction(p, p, TEMP_HP)
        );
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
