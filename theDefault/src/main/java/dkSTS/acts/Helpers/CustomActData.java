package dkSTS.acts.Helpers;

import com.badlogic.gdx.graphics.Color;
import dkSTS.util.AscensionVariable;

public class CustomActData {

    public Color FADE_COLOR;
    public Color SOURCE_FADE_COLOR;

    public String BG_MUSIC_ID;

    public int WEAK_ENEMIES;
    public int STRONG_ENEMIES;
    public int ELITES;

    public float SHOP_CHANCE = 0.05F;
    public float REST_CHANCE = 0.12F;
    public float TREASURE_CHANCE = 0.0F;
    public float EVENT_CHANCE = 0.22F;
    public float ELITE_CHANCE = 0.08F;
    public int SMALL_CHEST = 50;
    public int MEDIUM_CHEST = 33;
    public int LARGE_CHEST = 17;
    public int COMMON_RELIC = 50;
    public int UNCOMMON_RELIC = 33;
    public int RARE_RELIC = 17;
    public float COLORLESS_RARE = 0.3F;
    public AscensionActVariable<Float> CARD_UPGRADE = new AscensionActVariable<Float>(0.5F, 0.25F);


    public String[] TEXT;
    public String NAME;
    public String ID;
}
