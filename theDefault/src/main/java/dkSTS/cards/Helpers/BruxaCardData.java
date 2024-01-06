package dkSTS.cards.Helpers;

import com.megacrit.cardcrawl.cards.AbstractCard;
import dkSTS.characters.TheBruxa;

import static com.megacrit.cardcrawl.cards.AbstractCard.*;

public class BruxaCardData {
    public String ID;
    public String IMG;

    public CardColor COLOR = TheBruxa.Enums.COLOR_BRUXA;
    public int COST = 1;
    public CardType TYPE = CardType.ATTACK;
    public CardRarity RARITY = CardRarity.COMMON;
    public CardTarget TARGET = CardTarget.NONE;
}
