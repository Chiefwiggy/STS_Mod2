package dkSTS.relics;

import com.evacipated.cardcrawl.mod.stslib.patches.core.AbstractCreature.TempHPField;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.MeatOnTheBone;
import dkSTS.DefaultMod;
import dkSTS.relics.Helpers.RelicData;
import dkSTS.relics.Helpers.RelicDataBuilder;

public class RocEggRelic extends AbstractCustomRelic {
    public static RelicData data = new RelicDataBuilder()
            .id(RocEggRelic.class)
            .tier(RelicTier.SHOP)
            .sfx(LandingSound.MAGICAL)
            .img_path("rocegg.png")
            .build();

    public RocEggRelic() {
        super(data);
    }


    @Override
    public void atEndOfBattle() {
        AbstractPlayer p = AbstractDungeon.player;


        int remainingWard = TempHPField.tempHp.get(p);
        if (p.hasRelic(MeatOnTheBone.ID)) {
            if ((float)p.currentHealth <= (float)p.maxHealth / 2.0F && p.currentHealth > 0 && (float)(p.currentHealth + remainingWard) > (float)p.maxHealth / 2.0F) {
                p.getRelic(MeatOnTheBone.ID).onTrigger();
            }
        }
        if (remainingWard > 0) {
            p.heal(remainingWard, true);
            flash();
        }

    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
