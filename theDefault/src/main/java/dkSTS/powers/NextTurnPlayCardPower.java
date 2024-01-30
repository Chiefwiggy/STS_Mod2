package dkSTS.powers;

import com.megacrit.cardcrawl.actions.common.PlayTopCardAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.UnlimboAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dkSTS.DefaultMod;
import dkSTS.powers.Helpers.PowerData;
import dkSTS.powers.Helpers.PowerDataBuilder;

public class NextTurnPlayCardPower extends AbstractCustomPower {
    public static PowerData data = new PowerDataBuilder()
            .id(NextTurnPlayCardPower.class)
            .img_path("placeholder_power")
            .notTurnBased()
            .buff()
            .build();

    private static int idOffset = 0;

    private final AbstractCard cardToPlay;

    public NextTurnPlayCardPower(final AbstractCreature owner, final AbstractCreature source, final int amount, final AbstractCard card) {
        super(data, owner, source, amount);
        cardToPlay = card;

        ID = data.POWER_ID + idOffset;
        idOffset++;

        updateDescription();
    }

    @Override
    public AbstractPower makeCopy() {
        return new NextTurnPlayCardPower(owner, source, amount, cardToPlay);
    }

    @Override
    public void atStartOfTurnPostDraw() {
        DefaultMod.logger.info("NNNNNNNNNNNNNNUMMMMMMMMMMMMMM: " + amount);
        playInstancesOfCard();
        RemoveSelf();
    }

    @Override
    public void onRemove() {

        super.onRemove();
    }

    @Override
    public void updateDescription() {
        if (cardToPlay != null)
            description = DESC[0] + cardToPlay.name + SwitchPlural(1, 2);
    }

    public void playInstancesOfCard() {
        for (int i = 0; i < amount; ++i) {
            AbstractCard card = cardToPlay.makeCopy();
            card.exhaustOnUseOnce = true;
            AbstractDungeon.player.limbo.group.add(card);
            card.current_y = -200.0F * Settings.scale;
            card.target_x = (float)Settings.WIDTH / 2.0F + 200.0F * Settings.xScale;
            card.target_y = (float)Settings.HEIGHT / 2.0F;
            card.targetAngle = 0.0F;
            card.lighten(false);
            card.drawScale = 0.12F;
            card.targetDrawScale = 0.75F;
            card.applyPowers();
            this.addToTop(new NewQueueCardAction(card, true, false, true));
            this.addToTop(new UnlimboAction(card));
            DefaultMod.logger.info("NNNNNNNNNNNNNNUMMMMMMMMMMMMMM: " + amount);
            if (!Settings.FAST_MODE) {
                this.addToTop(new WaitAction(Settings.ACTION_DUR_MED));
            } else {
                this.addToTop(new WaitAction(Settings.ACTION_DUR_FASTER));
            }
        }
    }
}
