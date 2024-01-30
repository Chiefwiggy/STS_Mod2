package dkSTS.acts.Helpers;

import actlikeit.dungeons.CustomDungeon;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.saveAndContinue.SaveFile;
import com.megacrit.cardcrawl.scenes.TheBeyondScene;

import java.util.ArrayList;

public abstract class AbstractCustomAct extends CustomDungeon {

    public CustomActData actData;

    public AbstractCustomAct(CustomActData data) {
        super(data.NAME, data.ID);
        actData = data;
        initializeCustomAct();
    }

    public AbstractCustomAct(CustomActData data, CustomDungeon cd, AbstractPlayer p, ArrayList<String> emptyList) {
        super(cd, p, emptyList);
        actData = data;
        initializeCustomAct();
    }

    public AbstractCustomAct(CustomActData data, CustomDungeon cd, AbstractPlayer p, SaveFile sf) {
        super(cd, p, sf);
        actData = data;
        initializeCustomAct();
    }

    private void initializeCustomAct() {
        //this.setMainMusic(actData.BG_MUSIC_ID);
    }

//    @Override
//    protected void generateMonsters() {
//        this.generateWeakEnemies(actData.WEAK_ENEMIES);
//        this.generateStrongEnemies(actData.STRONG_ENEMIES);
//        this.generateElites(actData.ELITES);
//    }


//    @Override
//    protected void initializeLevelSpecificChances() {
//        shopRoomChance = actData.SHOP_CHANCE;
//        restRoomChance = actData.REST_CHANCE;
//        treasureRoomChance = actData.TREASURE_CHANCE;
//        eventRoomChance = actData.EVENT_CHANCE;
//        eliteRoomChance = actData.ELITE_CHANCE;
//        smallChestChance = actData.SMALL_CHEST;
//        mediumChestChance = actData.MEDIUM_CHEST;
//        largeChestChance = actData.LARGE_CHEST;
//        commonRelicChance = actData.COMMON_RELIC;
//        uncommonRelicChance = actData.UNCOMMON_RELIC;
//        rareRelicChance = actData.RARE_RELIC;
//        colorlessRareChance = actData.COLORLESS_RARE;
//        cardUpgradedChance = actData.CARD_UPGRADE.get();
//    }
}
