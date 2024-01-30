package dkSTS.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import dkSTS.relics.AbstractCustomRelic;

public class OnIncreaseMaxHPPatch {

    @SpirePatch(
            clz= AbstractCreature.class,
            method="increaseMaxHp"
    )
    public static class AfterIncreasePatch {
        @SpirePostfixPatch
        public static void Insert(AbstractCreature __instance, int amount, boolean showEffect) {

            if (__instance instanceof AbstractPlayer) {
                AbstractPlayer p = AbstractDungeon.player;

                for (AbstractRelic r : p.relics) {
                    try {
                        AbstractCustomRelic acr = (AbstractCustomRelic)r;
                        acr.onIncreaseMaxHealth(amount);
                    } catch (Exception ignored) {}
                }
            }
        }
    }

    @SpirePatch(
            clz=AbstractCreature.class,
            method="decreaseMaxHealth"
    )
    public static class AfterDecreasePatch {
        @SpirePostfixPatch
        public static void Insert(AbstractCreature __instance, int amount) {

            if (__instance instanceof AbstractPlayer) {
                AbstractPlayer p = AbstractDungeon.player;

                for (AbstractRelic r : p.relics) {
                    try {
                        AbstractCustomRelic acr = (AbstractCustomRelic)r;
                        acr.onDecreaseMaxHealth(amount);
                    } catch (Exception ignored) {}
                }
            }

        }
    }
}
