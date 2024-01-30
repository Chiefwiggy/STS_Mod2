package dkSTS.potions;

import basemod.abstracts.CustomPotion;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import dkSTS.potions.Helpers.PotionData;

public abstract class AbstractCustomPotion extends CustomPotion {

    int basePotency;
    int upgradePotency;

    protected String[] DESC;

    private final boolean combatOnly;

    public AbstractCustomPotion(PotionData data) {
        super(data.NAME, data.ID, data.RARITY, data.SIZE, data.COLOR);

        DESC = data.DESCRIPTIONS;
        isThrown = data.IS_THROWN;
        targetRequired = data.IS_THROWN;
        basePotency = data.POTENCY;

        potency = basePotency;
        combatOnly = data.COMBAT_ONLY;



        updateDescription();
    }

    private void updateDescription() {
        description = setDescription();
        tips.add(new PowerTip(name, description));
    }

    public abstract String setDescription();

    public void upgradePotion() {
        potency = upgradePotency;
        tips.clear();
        updateDescription();
    }

    protected boolean specialUseCondition() {return super.canUse();}

    @Override
    public final int getPotency(int i) {
        return potency;
    }

    @Override
    public final boolean canUse() {
        if (combatOnly && !(AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT)) {
            return false;
        }
        return specialUseCondition();
    }
}
