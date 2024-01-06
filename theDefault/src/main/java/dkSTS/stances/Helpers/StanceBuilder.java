package dkSTS.stances.Helpers;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.StanceStrings;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import dkSTS.DefaultMod;
import dkSTS.effects.AbstractStanceAuraEffect;
import dkSTS.effects.AbstractStanceParticleEffect;

public class StanceBuilder {
    private StanceData data;

    public StanceBuilder() {
        data = new StanceData();
    }

    public StanceBuilder id(Class<?> clazz) {
        data.ID = DefaultMod.makeID(clazz.getSimpleName());
        return this;
    }


    public StanceData build() {

        StanceStrings stanceString = CardCrawlGame.languagePack.getStanceString(data.ID);
        data.NAME = stanceString.NAME;
        data.DESCRIPTION = stanceString.DESCRIPTION;

        return data;
    }
}
