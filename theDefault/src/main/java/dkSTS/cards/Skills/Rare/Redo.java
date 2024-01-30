package dkSTS.cards.Skills.Rare;

import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.FiendFire;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import dkSTS.actions.RedrawCardAction;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.cards.Abstracts.AbstractFluxCard;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;

public class Redo extends AbstractFluxCard {
    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(Redo.class)
            .img("Redo.png")
            .rarity(CardRarity.RARE)
            .target(CardTarget.NONE)
            .type(CardType.SKILL)
            .x()
            .build();

    public static final int EXTRA_CARDS = 0;
    public static final int EXTRA_UPGRADE = 1;

    public Redo() {
        super(data);

        baseMagicNumber = magicNumber = EXTRA_CARDS;
    }

    @Override
    protected void UpgradeParameters() {
        upgradeMagicNumber(EXTRA_UPGRADE);
        UpgradeDescription();
    }

    @Override
    public void useAlways(AbstractPlayer p, AbstractMonster m) {

        for (AbstractCard c : p.hand.group) {
            this.addToBottom(
                    new DiscardSpecificCardAction(c)
            );
        }

        if (this.energyOnUse < EnergyPanel.totalCount) {
            this.energyOnUse = EnergyPanel.totalCount;
        }

        int effect = energyOnUse + magicNumber;

        if (p.hasRelic("Chemical X")) {
            effect += 2;
            p.getRelic("Chemical X").flash();
        }

        exhaust = true;
        this.addToBottom(
                new RedrawCardAction(p, effect, true)
        );

        if (!this.freeToPlayOnce) {
            p.energy.use(EnergyPanel.totalCount);
        }
    }

    @Override
    public void useOnFlux(AbstractPlayer p, AbstractMonster m) {
        exhaust = false;
    }
}
