package dkSTS.cards.Attacks.Common;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ExplosionSmallEffect;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;
import dkSTS.cards.Helpers.DamageActionBuilder;

public class BackupPistol extends AbstractBruxaCard {

    private static final int DAMAGE = 5;
    private static final int DAMAGE_UPGRADE = 2;

    private static final int TIMES = 2;

    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(BackupPistol.class)
            .img("BackupPistol.png")
            .rarity(CardRarity.COMMON)
            .target(CardTarget.ENEMY)
            .build();

    public BackupPistol() {
        super(data);

        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = TIMES;
    }

    @Override
    protected void UpgradeParameters() {
        upgradeDamage(DAMAGE_UPGRADE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m != null) {
            if (Settings.FAST_MODE) {
                this.addToBot(new VFXAction(new ExplosionSmallEffect(m.hb.cX, m.hb.cY - 40.0F * Settings.scale), 0.1F));
            } else {
                this.addToBot(new VFXAction(new ExplosionSmallEffect(m.hb.cX, m.hb.cY - 40.0F * Settings.scale), 0.3F));
            }
        }
        addToBottom(
                new DamageActionBuilder()
                        .damage(damage)
                        .target(m)
                        .build()
        );
        if (m != null) {
            if (Settings.FAST_MODE) {
                this.addToBot(new VFXAction(new ExplosionSmallEffect(m.hb.cX, m.hb.cY - 40.0F * Settings.scale), 0.1F));
            } else {
                this.addToBot(new VFXAction(new ExplosionSmallEffect(m.hb.cX, m.hb.cY - 40.0F * Settings.scale), 0.3F));
            }
        }
        addToBottom(
                new DamageActionBuilder()
                        .damage(damage)
                        .target(m)
                        .build()
        );
    }
}
