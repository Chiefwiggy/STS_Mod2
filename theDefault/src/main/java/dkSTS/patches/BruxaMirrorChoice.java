package dkSTS.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.TheBeyond;
import com.megacrit.cardcrawl.dungeons.TheCity;
import com.megacrit.cardcrawl.map.MapGenerator;
import com.megacrit.cardcrawl.map.MapRoomNode;
import com.megacrit.cardcrawl.rooms.EventRoom;
import dkSTS.characters.TheBruxa;
import dkSTS.relics.AncestorsDebt;
import dkSTS.util.MirrorRoom;
import javassist.CtBehavior;

import java.util.ArrayList;

public class BruxaMirrorChoice {

    @SpirePatch(
            clz= AbstractDungeon.class,
            method="generateMap"
    )
    public static class BruxaMirrorPatch {
        @SpirePostfixPatch
        public static void AddMirrorRoom() {
            if (AbstractDungeon.player.getCardColor().equals(TheBruxa.Enums.COLOR_BRUXA) && !((TheBruxa)AbstractDungeon.player).hasTriggeredMirrorEvent) {
                if ((AbstractDungeon.id.equals(TheCity.ID) || AbstractDungeon.id.equals(TheBeyond.ID))) {
                    ArrayList<MapRoomNode> eventNodes = new ArrayList<>();
                    for (int i = 0; i < AbstractDungeon.map.size(); ++i) {
                        for (int j = 0; j < AbstractDungeon.map.get(i).size(); ++j) {
                            if (AbstractDungeon.map.get(i).get(j).room instanceof EventRoom) {
                                eventNodes.add(AbstractDungeon.map.get(i).get(j));
                            }
                        }
                    }

                    MapRoomNode chosenNode = eventNodes.get(AbstractDungeon.mapRng.random(0, eventNodes.size()-1));
                    chosenNode.setRoom(new MirrorRoom());
                }
            }

        }
    }

//    private static class Locator extends SpireInsertLocator
//    {
//        @Override
//        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception
//        {
//            Matcher finalMatcher = new Matcher.MethodCallMatcher(MapGenerator.class, "toString");
//            return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
//        }
//    }
}
