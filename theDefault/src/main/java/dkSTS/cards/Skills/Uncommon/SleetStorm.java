package dkSTS.cards.Skills.Uncommon;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.cards.Helpers.ApplyPowerBuilder;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;
import dkSTS.powers.EvasionPower;
import dkSTS.powers.FrostPower;

import java.util.ArrayList;

public class SleetStorm extends AbstractBruxaCard {
    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(SleetStorm.class)
            .img("Skill.png")
            .rarity(CardRarity.UNCOMMON)
            .type(CardType.SKILL)
            .target(CardTarget.ALL)
            .cost(2)
            .build();

    private static final int FROST = 3;

    private static final int FROST_UPGRADE = 1;

    private static final int EVASION = 1;

    private static final int EVASION_UPGRADE = 1;

    public SleetStorm() {
        super(data);

        baseMagicNumber = magicNumber = FROST;
        defaultBaseSecondMagicNumber = defaultSecondMagicNumber = EVASION;
        cardsToPreview = new Burn();
    }

    @Override
    protected void UpgradeParameters() {
        upgradeMagicNumber(FROST_UPGRADE);
        upgradeDefaultSecondMagicNumber(EVASION_UPGRADE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        new ApplyPowerBuilder()
                .power(FrostPower.class)
                .targetAll()
                .amount(magicNumber)
                .addToBottom();

        new ApplyPowerBuilder()
                .power(EvasionPower.class)
                .amount(defaultSecondMagicNumber)
                .addToBottom();


        ArrayList<AbstractCard> cardsInHand = p.hand.getCardsOfType(CardType.STATUS).group;

        for (AbstractCard card : cardsInHand) {
            if (card.cardID.equals(Burn.ID)) {
                addToBottom(
                        new ExhaustSpecificCardAction(card, p.hand)
                );
            }
        }
    }

}
