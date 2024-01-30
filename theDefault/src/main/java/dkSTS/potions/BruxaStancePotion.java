package dkSTS.potions;

import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import dkSTS.cards.HelperCards.BecomeBloodthirsty;
import dkSTS.cards.HelperCards.BecomeInvisible;
import dkSTS.potions.Helpers.PotionData;
import dkSTS.potions.Helpers.PotionDataBuilder;

import java.util.ArrayList;

public class BruxaStancePotion extends AbstractCustomPotion {
//    public static PotionData data = new PotionDataBuilder()
//            .id(BruxaStancePotion.class)
//            .combatOnly()
//            .size(PotionSize.M)
//            .color_enum(PotionColor.SMOKE)
//            .rarity(PotionRarity.COMMON)
//            .liquid(192, 192, 192)
//            .hybrid(127, 127, 127)
//            .spots(232, 32, 32)
//            .potency(1)
//            .build();

    public static PotionData data = new PotionDataBuilder()
            .id(BruxaStancePotion.class)
            .combatOnly()
            .thrown()
            .color_enum(PotionColor.SMOKE)
            .size(PotionSize.M)
            .rarity(PotionRarity.COMMON)
            .liquid(0,0,255f)
            .hybrid(35, 35, 212)
            .spots(0,33,128)
            .potency(3)
            .build();

    public BruxaStancePotion() {
        super(data);
    }

    @Override
    public String setDescription() {
        return DESC[0];
    }

    @Override
    public AbstractPotion makeCopy() {
        return new BruxaStancePotion();
    }

    @Override
    public void use(AbstractCreature abstractCreature) {
        ArrayList<AbstractCard> cards = new ArrayList<>();

        cards.add(new BecomeBloodthirsty());
        cards.add(new BecomeInvisible());

        this.addToBot(
                new ChooseOneAction(cards)
        );
    }
}
