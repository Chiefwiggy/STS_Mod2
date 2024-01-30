package dkSTS.cards.Attacks.Uncommon;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;
import dkSTS.cards.Helpers.DamageActionBuilder;
import dkSTS.stances.InvisibilityStance;

public class ShadowStrike extends AbstractBruxaCard {
    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(ShadowStrike.class)
            .img("ShadowStrike.png")
            .rarity(CardRarity.UNCOMMON)
            .target(CardTarget.ENEMY)
            .type(CardType.ATTACK)
            .cost(1)
            .build();

    private static final int BASE_DAMAGE = 9;
    private static final int DAMAGE_UPGRADE = 5;
    private static final int ENERGY_GAINED = 2;

    public ShadowStrike() {
        super(data);

        baseDamage = damage = BASE_DAMAGE;
        tags.add(CardTags.STRIKE);
    }

    @Override
    protected void UpgradeParameters() {
        upgradeDamage(DAMAGE_UPGRADE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        new DamageActionBuilder()
                .damage(damage)
                .target(m)
                .addToBottom();

        if (p.stance.ID.equals(InvisibilityStance.data.ID)) {
            this.addToBottom(
                    new GainEnergyAction(ENERGY_GAINED)
            );
        }
    }

    @Override
    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        if (AbstractDungeon.player.stance.ID.equals(InvisibilityStance.data.ID)) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
    }
}
