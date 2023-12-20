package dkSTS.relics.Helpers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import static com.megacrit.cardcrawl.relics.AbstractRelic.*;

public class RelicData {
    public String ID;
    public String IMG_PATH;
    public RelicTier TIER = RelicTier.COMMON;
    public LandingSound SFX = LandingSound.MAGICAL;

    //generated
    public Texture IMG;
    public Texture OUTLINE;
}
