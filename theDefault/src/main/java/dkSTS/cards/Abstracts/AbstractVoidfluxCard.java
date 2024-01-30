package dkSTS.cards.Abstracts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.VoidCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.powers.BloodPower;
import dkSTS.powers.EmbraceTheVoidPower;

public abstract class AbstractVoidfluxCard extends AbstractBruxaCard {
    int r,g,b;

    protected float voidAnimTime;

    public AbstractVoidfluxCard(BruxaCardData data) {
        super(data);
        r = 0;
        g = 100;
        b = 200;
        voidAnimTime = 0.0f;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        useAlways(abstractPlayer, abstractMonster);
        if (isVoid()) {
            useOnFlux(abstractPlayer, abstractMonster);
        }
    }

    public void changeOnVoid() {}
    public void changeOnNotVoid() {}

    @Override
    public void applyPowers() {
        super.applyPowers();
        if (isVoid()) {
            changeOnVoid();
        } else {
            changeOnNotVoid();
        }
    }

    public abstract void useAlways(AbstractPlayer p, AbstractMonster m);

    public abstract void useOnFlux(AbstractPlayer p, AbstractMonster m);

    protected boolean isVoid() {
        try {
            boolean isVoid = false;
            AbstractPlayer p = AbstractDungeon.player;

            if (p.hand.findCardById(VoidCard.ID) != null || p.hasPower(EmbraceTheVoidPower.data.POWER_ID)) {
                isVoid = true;
            } else {
                int debuffs = 0;
                for (AbstractPower pow : p.powers) {
                    if (pow.type == AbstractPower.PowerType.DEBUFF || pow.ID.equals(BloodPower.data.POWER_ID)) {
                        debuffs++;
                    }
                    if (debuffs >= 3) {
                        isVoid = true;
                        break;
                    }
                }
            }
            return isVoid;
        } catch (Exception e) {
            return false;
        }
    }

    protected void extendRender(SpriteBatch sb) {}

    @Override
    public final void render(SpriteBatch sb) {

        voidAnimTime += Gdx.graphics.getDeltaTime()*2.0f;
        if (isVoid()) {
            float bsin = 0.575f + MathUtils.sin(voidAnimTime + MathUtils.PI)*0.425f;

            this.glowColor.r = Math.max(0.5f + MathUtils.sin(voidAnimTime)*0.5f, bsin);
            this.glowColor.g = 0.15f;
            this.glowColor.b = bsin;
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }


        extendRender(sb);
        super.render(sb);
    }
}
