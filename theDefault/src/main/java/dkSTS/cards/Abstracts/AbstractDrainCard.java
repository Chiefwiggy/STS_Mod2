package dkSTS.cards.Abstracts;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.DamageActionBuilder;
import dkSTS.powers.BloodPower;
import dkSTS.powers.VitalityPower;

public abstract class AbstractDrainCard extends AbstractBruxaCard {
    public AbstractDrainCard(BruxaCardData data) {
        super(data);
    }

    public void applyPowers() {
        int realBaseHeal = baseHeal;
        if (AbstractDungeon.player.hasPower(VitalityPower.data.POWER_ID)) {
            baseHeal += AbstractDungeon.player.getPower(VitalityPower.data.POWER_ID).amount;
        }
        super.applyPowers();
        heal = baseHeal;
        baseHeal = realBaseHeal;
        this.isHealModified = this.heal != this.baseHeal;
    }

    @Override
    public final void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        this.addToBottom(
                new ReducePowerAction(p, p, BloodPower.data.POWER_ID, 1)
        );
        postHungerUse(p, abstractMonster);
        new DamageActionBuilder()
                .damage(heal)
                .damageType(DamageInfo.DamageType.HP_LOSS)
                .target(abstractMonster)
                .addToBottom();
    }

    public abstract void postHungerUse(AbstractPlayer p, AbstractMonster m);
}
