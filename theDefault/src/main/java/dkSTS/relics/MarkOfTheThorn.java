package dkSTS.relics;

import com.badlogic.gdx.math.MathUtils;
import dkSTS.relics.Helpers.RelicData;
import dkSTS.relics.Helpers.RelicDataBuilder;

public class MarkOfTheThorn extends AbstractCustomRelic {
    public static RelicData data = new RelicDataBuilder()
            .id(MarkOfTheThorn.class)
            .sfx(LandingSound.MAGICAL)
            .tier(RelicTier.SPECIAL)
            .img_path("mark_of_the_thorn.png")
            .build();

    public MarkOfTheThorn() {
        super(data);
    }

    @Override
    public int onPlayerHeal(int healAmount) {
        this.flash();
        return MathUtils.round(.5f*super.onPlayerHeal(healAmount));
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
