package dkSTS.relics;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.BustedCrown;
import dkSTS.relics.Helpers.RelicData;
import dkSTS.relics.Helpers.RelicDataBuilder;

public class MarkOfEnervation extends AbstractCustomRelic {
    public static RelicData data = new RelicDataBuilder()
            .id(MarkOfEnervation.class)
            .img_path("mark_of_enervation.png")
            .sfx(LandingSound.SOLID)
            .tier(RelicTier.SPECIAL)
            .build();

    public MarkOfEnervation() {
        super(data);
    }

    @Override
    public void onEquip() {
        --AbstractDungeon.player.energy.energyMaster;
    }

    @Override
    public void onUnequip() {
        ++AbstractDungeon.player.energy.energyMaster;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
