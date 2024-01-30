package dkSTS.cards.Attacks.Uncommon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import dkSTS.cards.Abstracts.AbstractWildCard;
import dkSTS.cards.Helpers.ApplyPowerBuilder;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;
import dkSTS.cards.Helpers.DamageActionBuilder;

public class FrenziedStrike extends AbstractWildCard {
    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(FrenziedStrike.class)
            .img("Attack.png")
            .cost(1)
            .type(CardType.ATTACK)
            .rarity(CardRarity.UNCOMMON)
            .target(CardTarget.ENEMY)
            .build();

    private static final int DAMAGE = 7;
    private static final int DAMAGE_UPGRADE = 2;

    private static final int COND_WEAK = 1;
    private static final int COND_WEAK_UPGRADE = 1;

    private float colorAnimTime;
    private boolean goBoth;

    public FrenziedStrike() {
        super(data);

        baseDamage = damage = DAMAGE;
        baseMagicNumber = magicNumber = COND_WEAK;
        colorAnimTime = 0.0f;
        goBoth = false;

        this.tags.add(CardTags.STRIKE);
    }

    @Override
    protected void UpgradeParameters() {
        upgradeDamage(DAMAGE_UPGRADE);
        upgradeMagicNumber(COND_WEAK_UPGRADE);
    }

    @Override
    public void wildUse(AbstractPlayer abstractPlayer, AbstractMonster m) {
        new DamageActionBuilder()
                .damage(damage)
                .target(m)
                .addToBottom();

        if (isPlayerBloodied()) {
            new DamageActionBuilder()
                    .damage(damage)
                    .target(m)
                    .addToBottom();

            new ApplyPowerBuilder()
                    .power(WeakPower.class)
                    .target(m)
                    .amount(magicNumber)
                    .addToBottom();
        }
    }

    @Override
    public void triggerOnGlowCheck() {
        goBoth = false;
        if (isWild() && isPlayerBloodied()) {
            goBoth = true;
        }
        else if (isPlayerBloodied()) {
            this.glowColor = Color.RED.cpy();
        }
        else if (isWild()) {
            this.glowColor = Color.FOREST.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        if (goBoth) {
            colorAnimTime += Gdx.graphics.getDeltaTime()*2.0f;

            this.glowColor.r = 0.5f + MathUtils.sin(colorAnimTime)*0.5f;
            this.glowColor.g = 0.5f + MathUtils.sin(MathUtils.PI2 - colorAnimTime)*0.5F;
            this.glowColor.b = 0.0f;
        }


        super.render(sb);
    }
}
