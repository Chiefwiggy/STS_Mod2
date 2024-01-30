package dkSTS.variables;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import dkSTS.cards.Abstracts.AbstractBruxaCard;

import static dkSTS.DefaultMod.makeID;

public class MagicThird extends DynamicVariable {
    @Override
    public String key() {
        return "M3";
    }

    @Override
    public boolean isModified(AbstractCard card) {
        return ((AbstractBruxaCard) card).isMagicThirdNumberModified;
    }

    @Override
    public int value(AbstractCard card) {
        return ((AbstractBruxaCard) card).__magicThird;
    }

    @Override
    public int baseValue(AbstractCard abstractCard) {
        return ((AbstractBruxaCard)abstractCard).__baseMagicThird;
    }

    @Override
    public boolean upgraded(AbstractCard abstractCard) {
        return ((AbstractBruxaCard)abstractCard).upgradedMagicThird;
    }
}
