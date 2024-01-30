package dkSTS.cards.Abstracts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import dkSTS.cards.Helpers.ApplyPowerBuilder;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.stances.BloodlustStance;
import dkSTS.stances.DireWolfStance;

public abstract class AbstractWildVoidfluxCard extends AbstractVoidfluxCard {

    public AbstractWildVoidfluxCard(BruxaCardData data) {
        super(data);
    }

    protected boolean isWild() {
        try {
            return (AbstractDungeon.player.stance.ID.equals(DireWolfStance.data.ID) || AbstractDungeon.player.stance.ID.equals(BloodlustStance.data.ID));
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        super.use(abstractPlayer, abstractMonster);
        if (isWild() ) {
            new ApplyPowerBuilder()
                    .power(VigorPower.class)
                    .amount(4)
                    .addToBottom();
        }
    }


    @Override
    public final void extendRender(SpriteBatch sb) {
        if (isVoid() && isWild()) {

            float bsin = 0.275f + MathUtils.sin(voidAnimTime + MathUtils.PI)*0.225f;
            float gsin = 0.275f + MathUtils.sin(voidAnimTime)*0.225F;

            this.glowColor.r = bsin;
            this.glowColor.g = gsin;
            this.glowColor.b = bsin;
        } else if (isWild()) {
            this.glowColor = Color.FOREST.cpy();
        }


    }
}
