package dkSTS.relics;

import basemod.abstracts.CustomRelic;
import dkSTS.relics.Helpers.RelicData;

public abstract class AbstractCustomRelic extends CustomRelic {

    public AbstractCustomRelic(RelicData data) {
        super(data.ID, data.IMG, data.OUTLINE, data.TIER, data.SFX);
    }

    public void onIncreaseMaxHealth(int amount) {}
    public void onDecreaseMaxHealth(int amount) {}

    public void atEndOfBattle() {}
}
