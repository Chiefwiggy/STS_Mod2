package dkSTS.cards.Helpers;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import dkSTS.DefaultMod;

import static com.megacrit.cardcrawl.cards.AbstractCard.*;

public class BruxaCardDataBuilder {

    private BruxaCardData cd;

    public BruxaCardDataBuilder() {
        cd = new BruxaCardData();
    }

    public BruxaCardDataBuilder id(Class<?> clazz) {
        cd.ID = DefaultMod.makeID(clazz.getSimpleName());
        return this;
    }

    public BruxaCardDataBuilder img(String img) {
        cd.IMG = img;
        return this;
    }

    public BruxaCardDataBuilder cost(int cost) {
        cd.COST = cost;
        return this;
    }

    public BruxaCardDataBuilder type(CardType type) {
        cd.TYPE = type;
        return this;
    }

    public BruxaCardDataBuilder rarity(CardRarity rarity) {
        cd.RARITY = rarity;
        return this;
    }

    public BruxaCardDataBuilder target(CardTarget target) {
        cd.TARGET = target;
        return this;
    }

    public BruxaCardData build() {
        return cd;
    }
}
