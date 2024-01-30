package dkSTS.cards.Skills.Uncommon;

import com.megacrit.cardcrawl.actions.common.EndTurnAction;
import com.megacrit.cardcrawl.actions.watcher.PressEndTurnButtonAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.curses.Normality;
import com.megacrit.cardcrawl.cards.green.GrandFinale;
import com.megacrit.cardcrawl.cards.purple.Conclude;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.cards.Helpers.ApplyPowerBuilder;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;
import dkSTS.powers.CloakPower;

public class MistyStep extends AbstractBruxaCard {
    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(MistyStep.class)
            .img("Skill.png")
            .type(CardType.SKILL)
            .target(CardTarget.SELF)
            .rarity(CardRarity.UNCOMMON)
            .cost(1)
            .build();

    private static final int TIMES = 1;

    public MistyStep() {
        super(data);

        baseMagicNumber = magicNumber = TIMES;
        exhaustOnUseOnce = true;
        isEthereal = true;
    }

    @Override
    protected void UpgradeParameters() {
        isEthereal = false;
        exhaustOnUseOnce = false;
        UpgradeDescription();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        new ApplyPowerBuilder()
                .power(CloakPower.class)
                .amount(magicNumber)
                .addToBottom();

        this.addToBottom(
                new PressEndTurnButtonAction()
        );
    }

    @Override
    public boolean canPlay(AbstractCard card) {
        if (card.equals(this) && AbstractDungeon.actionManager.cardsPlayedThisTurn.size() != 1) {
            card.cantUseMessage = EXTENDED_DESCRIPTIONS[0];
            return false;
        }
        return true;
    }

    @Override
    public void triggerOnGlowCheck() {
        this.glowColor = GOLD_BORDER_GLOW_COLOR;
    }
}
