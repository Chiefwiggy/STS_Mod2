package dkSTS.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import dkSTS.DefaultMod;
import dkSTS.powers.AbstractCustomPower;
import javassist.CtBehavior;

public class TwoAmountPowerStack {

    @SpirePatch(
            clz= AbstractCreature.class,
            method="addPower"
    )
    public static class StackPower {

        @SpireInsertPatch(
                locator=Locator.class,
                localvars={"p"}
        )
        public static SpireReturn<Void> Insert(AbstractCreature __instance, AbstractPower powerToApply, AbstractPower p) {
            DefaultMod.logger.info("XXXXXXXXXXXXXXXXXXXXXXXXXX invoked");
            try {
                AbstractCustomPower cp = (AbstractCustomPower)p;
                AbstractCustomPower cp2 = (AbstractCustomPower)powerToApply;
                cp.stackPower2(cp2.amount2);
            } catch (Exception ignored) {}

            return SpireReturn.Continue();
        }
    }

    @SpirePatch(
            clz= ApplyPowerAction.class,
            method="update"
    )
    public static class StackPowerAction {

        @SpireInsertPatch(
                locator=Locator.class,
                localvars={"p"}
        )
        public static SpireReturn<Void> Insert(ApplyPowerAction __instance, AbstractPower p) {
            DefaultMod.logger.info("XXXXXXXXXXXXXXXXXXXXXXXXXX=2=2=2 invoked");
            try {
                AbstractCustomPower cp = (AbstractCustomPower)p;
                cp.stackPower2(__instance.amount);
            } catch (Exception ignored) {}

            return SpireReturn.Continue();
        }
    }



    private static class Locator extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctBehavior) throws Exception {
            final Matcher matcher = new Matcher.MethodCallMatcher(AbstractPower.class, "stackPower");
            return LineFinder.findInOrder(ctBehavior, matcher);
        }
    }



}
