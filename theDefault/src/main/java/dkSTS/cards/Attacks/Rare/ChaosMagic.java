package dkSTS.cards.Attacks.Rare;

import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.UnlimboAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dkSTS.cards.Abstracts.AbstractFluxCard;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;
import dkSTS.cards.Helpers.DamageActionBuilder;
import dkSTS.characters.TheBruxa;

public class ChaosMagic extends AbstractFluxCard {
    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(ChaosMagic.class)
            .img("ChaosMagic.png")
            .type(CardType.ATTACK)
            .rarity(CardRarity.RARE)
            .target(CardTarget.ALL)
            .cost(2)
            .build();

    private static final int DAMAGE = 4;
    private static final int DAMAGE_UPGRADE = 1;

    private static final int CARDS = 1;
    private static final int CARDS_UPGRADE = 1;

    public ChaosMagic() {
        super(data);

        baseDamage = damage = DAMAGE;
        baseMagicNumber = magicNumber = CARDS;
    }

    @Override
    protected void UpgradeParameters() {
        upgradeDamage(DAMAGE_UPGRADE);
        upgradeMagicNumber(CARDS_UPGRADE);
        UpgradeDescription();
    }

    @Override
    public void useAlways(AbstractPlayer p, AbstractMonster m) {
        new DamageActionBuilder()
                .damage(damage)
                .damageType(DamageInfo.DamageType.NORMAL)
                .targetRandom()
                .repeat(3)
                .addToBottom();
    }

    @Override
    public void useOnFlux(AbstractPlayer p, AbstractMonster m) {
        CardGroup g = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        g.group = CardLibrary.getAllCards();
        g.group.addAll(CardLibrary.getCardList(TheBruxa.Enums.LIBRARY_COLOR));

        for (int i = 0; i < magicNumber; ++i) {

            AbstractMonster mon = AbstractDungeon.getCurrRoom().monsters.getRandomMonster(true);

            AbstractCard card = g.getRandomCard(true).makeCopy();
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
            this.addToTop(new NewQueueCardAction(card, mon, false, true));
            this.addToTop(new UnlimboAction(card));
            if (!Settings.FAST_MODE) {
                this.addToTop(new WaitAction(Settings.ACTION_DUR_MED));
            } else {
                this.addToTop(new WaitAction(Settings.ACTION_DUR_FASTER));
            }

        }
    }
}
