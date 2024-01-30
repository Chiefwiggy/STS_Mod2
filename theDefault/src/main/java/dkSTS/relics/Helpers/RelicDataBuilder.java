package dkSTS.relics.Helpers;

import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.AbstractRelic.RelicTier;
import dkSTS.DefaultMod;
import dkSTS.util.TextureLoader;

public class RelicDataBuilder {

    private RelicData rd;

    public RelicDataBuilder() {
        rd = new RelicData();
    }

    public RelicDataBuilder id(Class<?> clazz) {
        rd.ID = DefaultMod.makeID(clazz.getSimpleName());
        return this;
    }

    public RelicDataBuilder img_path(String path) {
        rd.IMG_PATH = path;
        return this;
    }

    public RelicDataBuilder tier(RelicTier tier) {
        rd.TIER = tier;
        return this;
    }

    public RelicDataBuilder sfx(AbstractRelic.LandingSound sfx) {
        rd.SFX = sfx;
        return this;
    }

    public RelicData build() {
        rd.IMG = TextureLoader.getTexture(DefaultMod.makeRelicPath(rd.IMG_PATH));
        rd.OUTLINE = TextureLoader.getTexture(DefaultMod.makeRelicOutlinePath(rd.IMG_PATH));
        return rd;
    }




}
