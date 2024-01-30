package dkSTS.variables;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.colorless.BandageUp;
import com.megacrit.cardcrawl.powers.StrengthPower;
import dkSTS.cards.Abstracts.AbstractBruxaCard;

public class HealVariable extends DynamicVariable {
    @Override
    public String key() {
        return "H";
    }

    @Override
    public boolean isModified(AbstractCard card) {
        return ((AbstractBruxaCard)card).isHealModified;
    }

    @Override
    public int value(AbstractCard abstractCard) {
        return abstractCard.heal;
    }

    @Override
    public int baseValue(AbstractCard abstractCard) {
        return abstractCard.baseHeal;
    }

    @Override
    public boolean upgraded(AbstractCard abstractCard) {
        return ((AbstractBruxaCard)abstractCard).upgradedHeal;
    }
}
