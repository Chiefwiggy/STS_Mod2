package dkSTS.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dkSTS.stances.AbstractBruxaStance;
import javassist.CannotCompileException;
import javassist.CtBehavior;

public class useCardStancePatch {

    @SpirePatch(clz = AbstractPlayer.class, method="useCard")
    public static class UseCard {

        @SpireInsertPatch(
                locator=Locator.class
        )
        public static SpireReturn<Void> Insert(AbstractPlayer __instance, AbstractCard c, AbstractMonster monster, int energyOnUse) {





            return SpireReturn.Continue();
        }

        private static class Locator extends SpireInsertLocator {
            public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
                final Matcher matcher = new Matcher.MethodCallMatcher(AbstractCard.class, "use");
                return LineFinder.findInOrder(ctMethodToPatch, matcher);
            }
        }
    }
}
