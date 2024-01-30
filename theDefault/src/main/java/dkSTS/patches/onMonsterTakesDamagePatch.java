package dkSTS.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dkSTS.stances.AbstractBruxaStance;
import javassist.CtBehavior;

public class onMonsterTakesDamagePatch {

    @SpirePatch(
            clz= AbstractMonster.class,
            method="damage"
    )
    public static class AfterTakesDamage {

        @SpireInsertPatch(
                locator=Locator.class,
                localvars={"damageAmount"}
        )
        public static SpireReturn<Void> Insert(AbstractMonster __instance, DamageInfo info, int damageAmount) {

            AbstractPlayer p = AbstractDungeon.player;
            try {
                AbstractBruxaStance bs = (AbstractBruxaStance) p.stance;
                bs.onMonsterAttacked(__instance, info, damageAmount);
            } catch (Exception ignored) {}


            return SpireReturn.Continue();
        }

    }

    private static class Locator extends SpireInsertLocator {

        @Override
        public int[] Locate(CtBehavior ctBehavior) throws Exception {
            Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractMonster.class, "lastDamageTaken");
            return LineFinder.findInOrder(ctBehavior, finalMatcher);
        }
    }


}
