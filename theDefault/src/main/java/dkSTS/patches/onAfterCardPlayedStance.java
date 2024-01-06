package dkSTS.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.stances.AbstractStance;
import dkSTS.stances.AbstractBruxaStance;

public class onAfterCardPlayedStance {


    @SpirePatch(
            clz= CardGroup.class,
            method="triggerOnOtherCardPlayed"
    )
    public static class AfterCardStance {

        @SpirePostfixPatch
        public static void Insert(CardGroup __instance, AbstractCard usedCard)  {
            AbstractStance stz = AbstractDungeon.player.stance;

            if (stz instanceof AbstractBruxaStance) {
                AbstractBruxaStance abx = (AbstractBruxaStance)stz;
                abx.onAfterCardPlayed(usedCard);
            }
        }
    }

}
