package dkSTS.potions.Helpers;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.potions.AbstractPotion;

public class PotionData {

    public String ID;

    public static final Color PLACEHOLDER_POTION_LIQUID = CardHelper.getColor(209.0f, 53.0f, 18.0f); // Orange-ish Red
    public static final Color PLACEHOLDER_POTION_HYBRID = CardHelper.getColor(255.0f, 230.0f, 230.0f); // Near White
    public static final Color PLACEHOLDER_POTION_SPOTS = CardHelper.getColor(100.0f, 25.0f, 10.0f); // Super Dark Red/Brown

    public AbstractPotion.PotionRarity RARITY = AbstractPotion.PotionRarity.COMMON;
    public AbstractPotion.PotionSize SIZE = AbstractPotion.PotionSize.M;
    public AbstractPotion.PotionColor COLOR = null;

    public Color LIQUID_COLOR = PLACEHOLDER_POTION_LIQUID;
    public Color HYBRID_COLOR = PLACEHOLDER_POTION_HYBRID;
    public Color SPOTS_COLOR = PLACEHOLDER_POTION_SPOTS;

    public int POTENCY = 1;

    public boolean IS_THROWN = false;

    public boolean COMBAT_ONLY = true;

    //generated
    public String NAME;
    public String[] DESCRIPTIONS;
    public PotionData() {

    }
}
