package dkSTS.acts;

import actlikeit.dungeons.CustomDungeon;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.TheBeyond;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.MonsterInfo;
import com.megacrit.cardcrawl.monsters.beyond.AwakenedOne;
import com.megacrit.cardcrawl.monsters.beyond.Maw;
import com.megacrit.cardcrawl.monsters.beyond.SpireGrowth;
import com.megacrit.cardcrawl.monsters.beyond.TimeEater;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.saveAndContinue.SaveFile;
import com.megacrit.cardcrawl.scenes.AbstractScene;
import com.megacrit.cardcrawl.scenes.TheBeyondScene;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import dkSTS.acts.Helpers.AbstractCustomAct;
import dkSTS.acts.Helpers.CustomActBuilder;
import dkSTS.acts.Helpers.CustomActData;

import java.util.ArrayList;
import java.util.Collections;

public class TheWilds extends AbstractCustomAct {
    public static CustomActData data = new CustomActBuilder()
            .id(TheWilds.class)
            .weak(2)
            .strong(12)
            .elites(10)
            .fade(Color.valueOf("140a1eff"), Color.valueOf("140a1eff"))
            .build();

    public TheWilds() {
        super(data);


    }

    public TheWilds(CustomDungeon cd, AbstractPlayer p, ArrayList<String> emptyList) {
        super(data, cd, p, emptyList);
    }

    public TheWilds(CustomDungeon cd, AbstractPlayer p, SaveFile sf) {
        super(data, cd, p, sf);
    }

    @Override
    public AbstractScene DungeonScene() {
        return new TheBeyondScene();
    }

    @Override
    public String getActNumberText() {
        return "Act ???";
    }

    @Override
    public String getOptionText() {
        return "Venture through the portal.";
    }

    @Override
    public String getAfterSelectText() {
        return "As you enter the portal, you feel a rush of warm air breeze past your face as you are hit with the stench of home... but something seems wrong. NL A figure made of wood and branch approaches, and before you have time to react, you find yourself in a Brambled spire... your form having changed into that of a jackalope... NL With nothing else to do, perhaps there will be answers at the top of this spire.";
    }

    @Override
    public String getBodyText() {
        return "At the top of the Spire, you notice a glowing green portal. It looks like home. NL Do you enter?";
    }

    protected void generateMonsters() {
        this.generateWeakEnemies(2);
        this.generateStrongEnemies(12);
        this.generateElites(10);
    }

    protected void generateWeakEnemies(int count) {
        ArrayList<MonsterInfo> monsters = new ArrayList();
        monsters.add(new MonsterInfo("3 Darklings", 2.0F));
        monsters.add(new MonsterInfo("Orb Walker", 2.0F));
        monsters.add(new MonsterInfo("3 Shapes", 2.0F));
        MonsterInfo.normalizeWeights(monsters);
        this.populateMonsterList(monsters, count, false);
    }

    protected void generateStrongEnemies(int count) {
        ArrayList<MonsterInfo> monsters = new ArrayList();
        monsters.add(new MonsterInfo("Spire Growth", 1.0F));
        monsters.add(new MonsterInfo("Transient", 1.0F));
        monsters.add(new MonsterInfo("4 Shapes", 1.0F));
        monsters.add(new MonsterInfo("Maw", 1.0F));
        monsters.add(new MonsterInfo("Sphere and 2 Shapes", 1.0F));
        monsters.add(new MonsterInfo("Jaw Worm Horde", 1.0F));
        monsters.add(new MonsterInfo("3 Darklings", 1.0F));
        monsters.add(new MonsterInfo("Writhing Mass", 1.0F));
        MonsterInfo.normalizeWeights(monsters);
        this.populateFirstStrongEnemy(monsters, this.generateExclusions());
        this.populateMonsterList(monsters, count, false);
    }

    protected void generateElites(int count) {
        ArrayList<MonsterInfo> monsters = new ArrayList();
        monsters.add(new MonsterInfo("Giant Head", 2.0F));
        monsters.add(new MonsterInfo("Nemesis", 2.0F));
        monsters.add(new MonsterInfo("Reptomancer", 2.0F));
        MonsterInfo.normalizeWeights(monsters);
        this.populateMonsterList(monsters, count, true);
    }

    protected ArrayList<String> generateExclusions() {
        ArrayList<String> retVal = new ArrayList();
        switch ((String)monsterList.get(monsterList.size() - 1)) {
            case "3 Darklings":
                retVal.add("3 Darklings");
                break;
            case "Orb Walker":
                retVal.add("Orb Walker");
                break;
            case "3 Shapes":
                retVal.add("4 Shapes");
        }

        return retVal;
    }

    protected void initializeBoss() {
        bossList.clear();
        if (Settings.isDailyRun) {
            bossList.add("Awakened One");
            bossList.add("Time Eater");
            bossList.add("Donu and Deca");
            Collections.shuffle(bossList, new java.util.Random(monsterRng.randomLong()));
        } else if (!UnlockTracker.isBossSeen("CROW")) {
            bossList.add("Awakened One");
        } else if (!UnlockTracker.isBossSeen("DONUT")) {
            bossList.add("Donu and Deca");
        } else if (!UnlockTracker.isBossSeen("WIZARD")) {
            bossList.add("Time Eater");
        } else {
            bossList.add("Awakened One");
            bossList.add("Time Eater");
            bossList.add("Donu and Deca");
            Collections.shuffle(bossList, new java.util.Random(monsterRng.randomLong()));
        }

        if (bossList.size() == 1) {
            bossList.add(bossList.get(0));
        } else if (bossList.isEmpty()) {
            logger.warn("Boss list was empty. How?");
            bossList.add("Awakened One");
            bossList.add("Time Eater");
            bossList.add("Donu and Deca");
            Collections.shuffle(bossList, new java.util.Random(monsterRng.randomLong()));
        }

    }

    protected void initializeEventList() {

    }


    protected void initializeShrineList() {

    }
}
