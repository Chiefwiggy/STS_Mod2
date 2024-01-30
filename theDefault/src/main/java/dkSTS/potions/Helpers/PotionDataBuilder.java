package dkSTS.potions.Helpers;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import dkSTS.DefaultMod;

public class PotionDataBuilder {

    private final PotionData data;

    public PotionDataBuilder() {
        data = new PotionData();
    }

    public PotionDataBuilder id(Class<?> clazz) {
        data.ID = DefaultMod.makeID(clazz.getSimpleName());
        return this;
    }

    public PotionDataBuilder potency(final int amount) {
        data.POTENCY = amount;
        return this;
    }

    public PotionDataBuilder thrown() {
        data.IS_THROWN = true;
        return this;
    }

    public PotionDataBuilder notThrown() {
        data.IS_THROWN = false;
        return this;
    }

    public PotionDataBuilder combatOnly() {
        data.COMBAT_ONLY = true;
        return this;
    }

    public PotionDataBuilder useAnytime() {
        data.COMBAT_ONLY = false;
        return this;
    }

    public PotionDataBuilder rarity(AbstractPotion.PotionRarity rarity) {
        data.RARITY = rarity;
        return this;
    }

    public PotionDataBuilder size(AbstractPotion.PotionSize size) {
        data.SIZE = size;
        return this;
    }

    public PotionDataBuilder color_enum(AbstractPotion.PotionColor color) {
        data.COLOR = color;
        return this;
    }

    public PotionDataBuilder liquid(Color color) {
        data.LIQUID_COLOR = color;
        return this;
    }

    public PotionDataBuilder liquid(float r, float g, float b) {
        data.LIQUID_COLOR = CardHelper.getColor(r, g, b);
        return this;
    }

    public PotionDataBuilder hybrid(Color color) {
        data.HYBRID_COLOR = color;
        return this;
    }

    public PotionDataBuilder hybrid(float r, float g, float b) {
        data.HYBRID_COLOR = CardHelper.getColor(r, g, b);
        return this;
    }

    public PotionDataBuilder spots(Color color) {
        data.SPOTS_COLOR = color;
        return this;
    }

    public PotionDataBuilder spots(float r, float g, float b) {
        data.SPOTS_COLOR = CardHelper.getColor(r, g, b);
        return this;
    }


    public PotionData build() {
        PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(data.ID);
        data.NAME = potionStrings.NAME;
        data.DESCRIPTIONS = potionStrings.DESCRIPTIONS;
        return data;
    }
}
