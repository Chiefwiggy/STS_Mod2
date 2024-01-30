package dkSTS.patches;

import com.evacipated.cardcrawl.mod.stslib.patches.core.AbstractCreature.TempHPField;
import com.evacipated.cardcrawl.mod.stslib.patches.tempHp.BattleEnd;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import dkSTS.DefaultMod;
import dkSTS.powers.AbstractCustomPower;
import dkSTS.relics.AbstractCustomRelic;
import javassist.CannotCompileException;
import javassist.CtBehavior;

public class EndOfBattlePatches {

    @SpirePatch(
            clz = AbstractRoom.class,
            method = "endBattle"
    )
    public static class OnEndOfBattle {

        @SpireInsertPatch(
                locator=Locator.class
        )
        public static SpireReturn<Void> Insert(AbstractRoom __instance) {

            AbstractPlayer p = AbstractDungeon.player;

            for (AbstractRelic r : p.relics) {
                try {
                    AbstractCustomRelic cr = (AbstractCustomRelic)r;
                    cr.atEndOfBattle();
                } catch (Exception ignored){}
            }

            for (AbstractPower pow : p.powers) {
                try {
                    AbstractCustomPower cp = (AbstractCustomPower)pow;
                    cp.atEndOfBattle();
                } catch (Exception ignored){}
            }


            TempHPField.tempHp.set(p, 0);

            return SpireReturn.Continue();
        }
    }

    private static class Locator extends SpireInsertLocator {
        public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
            final Matcher matcher = new Matcher.FieldAccessMatcher(AbstractRoom.class, "isBattleOver");

            return LineFinder.findInOrder(ctMethodToPatch, matcher);
        }
    }

    @SpirePatch(
            clz= BattleEnd.class,
            method="Prefix"
    )
    public static class RemoveTempHPPatch {
        public static void Replace(BattleEnd __instance) {

        }
    }
}
