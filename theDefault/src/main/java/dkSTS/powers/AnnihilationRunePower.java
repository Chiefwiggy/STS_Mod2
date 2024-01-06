package dkSTS.powers;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.purple.Judgement;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.FallingIceEffect;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;
import dkSTS.cards.Helpers.DamageActionBuilder;
import dkSTS.powers.Helpers.PowerData;
import dkSTS.powers.Helpers.PowerDataBuilder;

public class AnnihilationRunePower extends AbstractRune {

    public static PowerData data = new PowerDataBuilder()
            .id(AnnihilationRunePower.class)
            .img_path("placeholder_power")
            .debuff()
            .build();

    public AnnihilationRunePower(final AbstractCreature owner, final AbstractCreature source, final int magic) {
        super(data, owner, source, magic);

        updateDescription();
    }
    @Override
    public AbstractPower makeCopy() {
        return new AnnihilationRunePower(owner, source, amount);
    }
    @Override
    public void whenRuneResolves() {
        addToTop(
                new DamageActionBuilder()
                        .target((AbstractMonster)owner)
                        .damageType(DamageInfo.DamageType.HP_LOSS)
                        .damage(magicNumber)
                        .build()
        );

        addAnimation(WeightyImpactEffect.class, Color.GOLD.cpy());
    }

    @Override
    public void updateDescription() {
        description = DESC[0] + SwitchPlural(1, 2, true) + DESC[3] + magicNumber + DESC[4];
    }
}
