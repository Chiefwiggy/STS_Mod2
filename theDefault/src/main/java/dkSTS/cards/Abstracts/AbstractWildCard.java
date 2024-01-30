package dkSTS.cards.Abstracts;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import dkSTS.cards.Helpers.ApplyPowerBuilder;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.stances.BloodlustStance;
import dkSTS.stances.DireWolfStance;

public abstract class AbstractWildCard extends AbstractBruxaCard {
    public AbstractWildCard(BruxaCardData data) {
        super(data);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        wildUse(p, m);
        if (isWild() ) {
            new ApplyPowerBuilder()
                    .power(VigorPower.class)
                    .amount(4)
                    .addToBottom();
        }
    }

    protected boolean isWild() {
        try {
            return (AbstractDungeon.player.stance.ID.equals(DireWolfStance.data.ID) || AbstractDungeon.player.stance.ID.equals(BloodlustStance.data.ID));
        } catch (Exception e) {
            return false;
        }

    }


    public abstract void wildUse(AbstractPlayer p, AbstractMonster m);

    @Override
    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        if (isWild()) {
            this.glowColor = Color.FOREST.cpy();
        }
    }
}
