package dkSTS.relics;

import com.megacrit.cardcrawl.relics.PaperFrog;
import dkSTS.relics.Helpers.RelicData;
import dkSTS.relics.Helpers.RelicDataBuilder;

public class BlastHammerRelic extends AbstractCustomRelic {
    public static RelicData data = new RelicDataBuilder()
            .id(BlastHammerRelic.class)
            .img_path("blasthammer.png")
            .tier(RelicTier.UNCOMMON)
            .sfx(LandingSound.CLINK)
            .build();

    public BlastHammerRelic() {
        super(data);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
