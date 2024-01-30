package dkSTS.powers;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.green.Choke;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ChokePower;
import dkSTS.DefaultMod;
import dkSTS.cards.Helpers.DamageActionBuilder;
import dkSTS.powers.Helpers.PowerData;
import dkSTS.powers.Helpers.PowerDataBuilder;

public class BadDreamPower extends AbstractCustomPower {
    public static PowerData data = new PowerDataBuilder()
            .id(BadDreamPower.class)
            .img_path("dream_eater")
            .debuff()
            .notTurnBased()
            .build();

    public BadDreamPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        super(data, owner, source, amount);
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {


        AbstractMonster mon = (AbstractMonster)owner;

        if (mon.getIntentBaseDmg() == -1) {
            new DamageActionBuilder()
                    .damage(amount)
                    .target(mon)
                    .damageType(DamageInfo.DamageType.HP_LOSS)
                    .addToBottom();
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new BadDreamPower(owner, source, amount);
    }

    @Override
    public void updateDescription() {
        description = DESC[0] + amount + DESC[1];
    }
}
