package dkSTS.cards.Skills.Rare;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.watcher.PressEndTurnButtonAction;
import com.megacrit.cardcrawl.actions.watcher.SkipEnemiesTurnAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.OfferingEffect;
import com.megacrit.cardcrawl.vfx.combat.WhirlwindEffect;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;

public class Metastasize extends AbstractBruxaCard {
    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(Metastasize.class)
            .img("Metastasize.png")
            .type(CardType.SKILL)
            .target(CardTarget.NONE)
            .rarity(CardRarity.RARE)
            .cost(1)
            .build();

    private static final int HP_LOSS = 3;
    private static final int HP_LOSS_UPGRADE = -1;

    public Metastasize() {
        super(data);
        baseMagicNumber = magicNumber = HP_LOSS;
        exhaust = exhaustOnUseOnce = true;
    }

    @Override
    protected void UpgradeParameters() {
        upgradeMagicNumber(HP_LOSS_UPGRADE);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        abstractPlayer.decreaseMaxHealth(magicNumber);
        this.addToBot(new VFXAction(new WhirlwindEffect(new Color(1.0F, 0.2F, 0.2F, 0.9F), true)));
        if (Settings.FAST_MODE) {
            this.addToBot(new VFXAction(new OfferingEffect(), 0.1F));
        } else {
            this.addToBot(new VFXAction(new OfferingEffect(), 0.5F));
        }
        this.addToBot(new SkipEnemiesTurnAction());
        this.addToBot(new PressEndTurnButtonAction());
    }




}
