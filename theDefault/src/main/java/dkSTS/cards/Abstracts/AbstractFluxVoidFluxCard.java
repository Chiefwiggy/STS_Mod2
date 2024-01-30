package dkSTS.cards.Abstracts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.powers.EmbraceTheVoidPower;

public abstract class AbstractFluxVoidFluxCard extends AbstractVoidfluxCard {
    public AbstractFluxVoidFluxCard(BruxaCardData data) {
        super(data);
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
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        useAlways(abstractPlayer, abstractMonster);
        if (isFlux()) {
            useOnFlux(abstractPlayer, abstractMonster);
        }
        if (isVoid()) {
            useOnVoidflux(abstractPlayer, abstractMonster);
        }
    }

    public abstract void useOnFlux(AbstractPlayer p, AbstractMonster m);
    public abstract void useOnVoidflux(AbstractPlayer p, AbstractMonster m);

    @Override
    protected void extendRender(SpriteBatch sb) {
        if (isVoid() && isFlux()) {
            if (MathUtils.floor(voidAnimTime / MathUtils.PI2) % 2 == 0) {
                this.glowColor.r = 0.5f + MathUtils.sin(voidAnimTime)*0.5f;
                this.glowColor.g = 0.5f + MathUtils.sin(voidAnimTime - 2.09439510239f)*0.5f;
                this.glowColor.b = 0.5f + MathUtils.sin(voidAnimTime + 2.09439510239f)*0.5f;
            } else {
                float bsin = 0.575f + MathUtils.sin(voidAnimTime + MathUtils.PI)*0.425f;

                this.glowColor.r = Math.max(0.5f + MathUtils.sin(voidAnimTime)*0.5f, bsin);
                this.glowColor.g = 0.15f;
                this.glowColor.b = bsin;
            }
        } else if (isFlux()) {
            this.glowColor.r = 0.5f + MathUtils.sin(voidAnimTime)*0.5f;
            this.glowColor.g = 0.5f + MathUtils.sin(voidAnimTime - 2.09439510239f)*0.5f;
            this.glowColor.b = 0.5f + MathUtils.sin(voidAnimTime + 2.09439510239f)*0.5f;
        }
    }
}
