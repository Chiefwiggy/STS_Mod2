package dkSTS.acts.Helpers;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import dkSTS.DefaultMod;

public class CustomActBuilder {

    private final CustomActData data;

    public CustomActBuilder() {
        data = new CustomActData();
    }

    public CustomActBuilder id(Class<?> clazz) {
        data.ID = DefaultMod.makeID(clazz.getSimpleName());
        return this;
    }

    public CustomActBuilder fade(Color fade, Color source) {
        data.FADE_COLOR = fade;
        data.SOURCE_FADE_COLOR = source;
        return this;
    }

    public CustomActBuilder weak(int amount) {
        data.WEAK_ENEMIES = amount;
        return this;
    }

    public CustomActBuilder strong(int amount) {
        data.STRONG_ENEMIES = amount;
        return this;
    }

    public CustomActBuilder elites(int amount) {
        data.ELITES = amount;
        return this;
    }

    public CustomActBuilder bg_music(String id) {
        data.BG_MUSIC_ID = id;
        return this;
    }

    public CustomActBuilder shop_chance(float chance) {
        data.SHOP_CHANCE = chance;
        return this;
    }

    public CustomActBuilder rest_chance(float chance) {
        data.REST_CHANCE = chance;
        return this;
    }

    public CustomActBuilder treasure_chance(float chance) {
        data.TREASURE_CHANCE = chance;
        return this;
    }

    public CustomActBuilder event_chance(float chance) {
        data.EVENT_CHANCE = chance;
        return this;
    }

    public CustomActBuilder elite_chance(float chance) {
        data.ELITE_CHANCE = chance;
        return this;
    }

    public CustomActBuilder chest_sizes(int small, int med, int lg) {
        data.SMALL_CHEST = small;
        data.MEDIUM_CHEST = med;
        data.LARGE_CHEST = lg;
        return this;
    }

    public CustomActBuilder relic_chances(int common, int uncommon, int rare) {
        data.COMMON_RELIC = common;
        data.UNCOMMON_RELIC = uncommon;
        data.RARE_RELIC = rare;
        return this;
    }

    public CustomActBuilder colorless_rare(int chance) {
        data.COLORLESS_RARE = chance;
        return this;
    }

    public CustomActBuilder upgrade_chance(float base, float a12) {
        data.CARD_UPGRADE = new AscensionActVariable<Float>(base, a12);
        return this;
    }

    public CustomActData build() {

        UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(data.ID);
        data.TEXT = uiStrings.TEXT;
        data.NAME = uiStrings.TEXT[0];

        return data;
    }
}
