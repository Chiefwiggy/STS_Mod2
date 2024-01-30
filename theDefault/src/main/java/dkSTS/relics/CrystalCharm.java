package dkSTS.relics;

import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.evacipated.cardcrawl.mod.stslib.patches.core.AbstractCreature.TempHPField;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.HealEffect;
import dkSTS.relics.Helpers.RelicData;
import dkSTS.relics.Helpers.RelicDataBuilder;

public class CrystalCharm extends AbstractCustomRelic {
    public static RelicData data = new RelicDataBuilder()
            .id(CrystalCharm.class)
            .img_path("crystal_pendant.png")
            .tier(RelicTier.UNCOMMON)
            .build();

    private static final int BLOCK_AMOUNT = 3;

    public CrystalCharm() {
        super(data);
    }

    public void InvokeEvasion() {
        AbstractPlayer p = AbstractDungeon.player;
        TempHPField.tempHp.set(p, (Integer)TempHPField.tempHp.get(p) + 3);
        AbstractDungeon.effectsQueue.add(new HealEffect(p.hb.cX - p.animX, p.hb.cY, 3));
        p.healthBarUpdatedEvent();
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + BLOCK_AMOUNT + DESCRIPTIONS[1];
    }
}
