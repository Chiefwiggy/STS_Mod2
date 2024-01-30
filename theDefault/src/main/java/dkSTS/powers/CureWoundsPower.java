package dkSTS.powers;

import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dkSTS.powers.Helpers.PowerData;
import dkSTS.powers.Helpers.PowerDataBuilder;

public class CureWoundsPower extends AbstractCustomPower {
    public static PowerData data = new PowerDataBuilder()
            .id(CureWoundsPower.class)
            .img_path("placeholder_power")
            .buff()
            .notTurnBased()
            .build();

    public CureWoundsPower(final AbstractCreature owner, final AbstractCreature source, int amount) {
        super(data, owner, source, amount);
    }


    @Override
    public AbstractPower makeCopy() {
        return new CureWoundsPower(owner, source, amount);
    }

    @Override
    public void onCardDraw(AbstractCard card) {
        if (card.type == AbstractCard.CardType.STATUS) {
            this.addToBot(
                    new HealAction(owner, owner, amount)
            );
        }
    }

    @Override
    public void updateDescription() {
        description = DESC[0] + this.amount + DESC[1];
    }
}
