package dkSTS.cards.Abstracts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.powers.EmbraceTheVoidPower;
import dkSTS.powers.FrostPower;

public abstract class AbstractFluxCard extends AbstractBruxaCard {

    int r, g, b;

    private float rainbowAnimTime;
    public AbstractFluxCard(BruxaCardData data) {
        super(data);

        r = 0;
        g = 100;
        b = 200;

        rainbowAnimTime = 0.0f;
    }

    @Override
    public final void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        useAlways(abstractPlayer, abstractMonster);
        if (isFlux()) {
            useOnFlux(abstractPlayer, abstractMonster);
        }

    }

    public abstract void useAlways(AbstractPlayer p, AbstractMonster m);
    public abstract void useOnFlux(AbstractPlayer p, AbstractMonster m);

    @Override
    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
    }

    public boolean isFlux() {
        try {
            boolean isValid = false;
            AbstractPlayer p = AbstractDungeon.player;
            int buffs = 0;
            if (p.hasPower(EmbraceTheVoidPower.data.POWER_ID)) {
                isValid = true;
            } else {
                for (AbstractPower pow : p.powers) {
                    if (pow.type == AbstractPower.PowerType.BUFF) {
                        buffs++;
                    }
                    if (buffs >= 4) {
                        isValid = true;
                        break;
                    }
                }
            }
            return isValid;
        } catch (Exception e) {
            return false;
        }

    }


    @Override
    public void render(SpriteBatch sb) {

        if (isFlux()) {
            rainbowAnimTime += Gdx.graphics.getDeltaTime()*2.0f;

            this.glowColor.r = 0.5f + MathUtils.sin(rainbowAnimTime)*0.5f;
            this.glowColor.g = 0.5f + MathUtils.sin(rainbowAnimTime - 2.09439510239f)*0.5f;
            this.glowColor.b = 0.5f + MathUtils.sin(rainbowAnimTime + 2.09439510239f)*0.5f;
        }



        super.render(sb);
    }
}
