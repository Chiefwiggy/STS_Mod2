package dkSTS.powers;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.cards.HelperCards.EchoTendril;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;
import dkSTS.powers.Helpers.PowerData;
import dkSTS.powers.Helpers.PowerDataBuilder;

public class EchoesOfTheVoidPower extends AbstractCustomPower {
    public static PowerData data = new PowerDataBuilder()
            .id(EchoesOfTheVoidPower.class)
            .img_path("placeholder_power")
            .notTurnBased()
            .buff()
            .build();

    public EchoesOfTheVoidPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        super(data, owner, source, amount);
    }

    @Override
    public AbstractPower makeCopy() {
        return new EchoesOfTheVoidPower(owner, source, amount);
    }

    @Override
    public void updateDescription() {
        description = DESC[0] + SwitchPlural(1,2,true) + DESC[3];
    }

    @Override
    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
        if (damageAmount > 0) {
            this.addToBot(
                    new MakeTempCardInHandAction(new EchoTendril())
            );
        }

        return super.onAttackedToChangeDamage(info, damageAmount);
    }
}
