package dkSTS.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.stances.AbstractBruxaStance;
import javassist.CannotCompileException;
import javassist.CtBehavior;

public class StanceExtensions {

    @SpirePatch(
            clz = AbstractPlayer.class,
            method = "damage"
    )
    public static class OnAttackedToChangeDamageClass {
        @SpireInsertPatch(
                locator=AttackedLocator.class,
                localvars = {"damageAmount"}
        )
        public static SpireReturn<Void> Insert(AbstractPlayer __instance, DamageInfo info, @ByRef int[] damageAmount) {
            try {
                AbstractBruxaStance ast = (AbstractBruxaStance) __instance.stance;
                damageAmount[0] = ast.onAttackedToChangeDamage(info, damageAmount[0]);
            } catch (Exception ignored) {}

            //easier to put this here...
            for (AbstractCard c : __instance.hand.group) {
                try {
                    AbstractBruxaCard bc = (AbstractBruxaCard)c;
                    bc.onReceiveDamageWhileInHand(info, damageAmount[0]);
                } catch (Exception ignored) {}
            }


            return SpireReturn.Continue();
        }
    }

    private static class AttackedLocator extends SpireInsertLocator {
        public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
            final Matcher matcher = new Matcher.MethodCallMatcher(AbstractRelic.class, "onAttacked");
            return LineFinder.findInOrder(ctMethodToPatch, matcher);
        }
    }

    @SpirePatch(
            clz=AbstractCard.class,
            method="applyPowersToBlock"
    )
    public static class ModifyBlockPatch {

        @SpireInsertPatch(
                locator=ModBlockLocator.class,
                localvars = {"tmp"}
        )
        public static SpireReturn<Void> Insert(AbstractCard __instance, @ByRef float[] tmp) {
            try {
                AbstractPlayer p = AbstractDungeon.player;
                AbstractBruxaStance ast = (AbstractBruxaStance) p.stance;
                tmp[0] = ast.modifyBlock(tmp[0], __instance);
            } catch (Exception ignored) {}


            return SpireReturn.Continue();
        }
    }

    private static class ModBlockLocator extends SpireInsertLocator {
        public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
            final Matcher matcher = new Matcher.MethodCallMatcher(AbstractPower.class, "modifyBlock");
            return LineFinder.findInOrder(ctMethodToPatch, matcher);
        }
    }

}
