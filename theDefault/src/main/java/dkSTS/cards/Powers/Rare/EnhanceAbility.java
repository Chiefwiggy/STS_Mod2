package dkSTS.cards.Powers.Rare;

import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.cards.HelperCards.*;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;

import java.util.ArrayList;

public class EnhanceAbility extends AbstractBruxaCard {

    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(EnhanceAbility.class)
            .img("EnhanceAbility.png")
            .rarity(CardRarity.RARE)
            .type(CardType.POWER)
            .target(CardTarget.SELF)
            .cost(2)
            .build();

    public EnhanceAbility() {
        super(data);

        isEthereal = true;
    }

    @Override
    protected void UpgradeParameters() {
        isEthereal = false;
        UpgradeDescription();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        ArrayList<AbstractCard> cards = new ArrayList<AbstractCard>();
//        cards.add(new ArchfeysWisdom());
        cards.add(new QuicklingsSpeed());
        cards.add(new RedcapsVitality());
        cards.add(new TitansStrength());
        cards.add(new UnicornsBeauty());

        addToBottom(
                new ChooseOneAction(cards)
        );
    }
}
