package dkSTS.relics;

import dkSTS.relics.Helpers.RelicData;
import dkSTS.relics.Helpers.RelicDataBuilder;

public class RimefrostPendantRelic extends AbstractCustomRelic {
    public static RelicData data = new RelicDataBuilder()
            .id(RimefrostPendantRelic.class)
            .img_path("rimefrost.png")
            .tier(RelicTier.UNCOMMON)
            .sfx(LandingSound.MAGICAL)
            .build();

    public RimefrostPendantRelic() {
        super(data);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
