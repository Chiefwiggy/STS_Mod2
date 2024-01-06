package dkSTS.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.screens.stats.StatsScreen;
import dkSTS.DefaultMod;
import dkSTS.powers.AbstractCustomPower;
import dkSTS.util.EvasionCounter;
import javassist.CannotCompileException;
import javassist.CtBehavior;

public class onMonsterTrueDeath {

    @SpirePatch(clz= AbstractMonster.class,method="die",paramtypez = {boolean.class})
    public static class onDeath {

        @SpireInsertPatch(
                locator=Locator.class
        )
        public static SpireReturn<Void> Insert(AbstractMonster __instance, boolean triggerRelics) {
            if (triggerRelics) {
                for (AbstractPower p : AbstractDungeon.player.powers) {
                    try {
                        AbstractCustomPower cp = (AbstractCustomPower)p;
                        cp.onMonsterTrueDeath();
                    } catch (Exception e) {
                        DefaultMod.logger.debug(p.name + " cannot be cast into a CustomPower");
                    }
                }
                EvasionCounter.IncrementCounter();
            }
            return SpireReturn.Continue();
        }

        private static class Locator extends SpireInsertLocator
        {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception
            {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(MonsterGroup.class, "areMonstersBasicallyDead");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }
}
