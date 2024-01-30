package dkSTS.cards.Skills.Uncommon;

import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.UpgradeSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.purple.LessonLearned;
import com.megacrit.cardcrawl.cards.red.SearingBlow;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;

public class BloodPalimpsest extends AbstractBruxaCard {
    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(BloodPalimpsest.class)
            .img("Palimpsest.png")
            .type(CardType.SKILL)
            .rarity(CardRarity.UNCOMMON)
            .target(CardTarget.NONE)
            .unplayable()
            .build();

    private static final int BLOCK = 20;

    public BloodPalimpsest() {
        super(data);

        baseBlock = block = BLOCK;
        this.timesUpgraded = 0;
    }

    @Override
    protected void UpgradeParameters() {}

    @Override
    public void upgrade() {
        if (timesUpgraded == 0) {
            timesUpgraded = 1;
            this.upgraded = true;
            this.name = this.name + "+";
            this.initializeTitle();
            UpgradeDescription();
            initializeDescription();
            upgradeBaseCost(1);


            isEthereal = true;
            exhaust = exhaustOnUseOnce = true;

        } else if (timesUpgraded == 1) {
            timesUpgraded = 2;
            this.name = this.name + "+";
            this.initializeTitle();
            rawDescription = EXTENDED_DESCRIPTIONS[0];
            initializeDescription();

            isEthereal = false;
        }
    }

    @Override
    public boolean canUpgrade() {
        return timesUpgraded < 2;
    }

    public void downgrade() {
        timesUpgraded = 0;
        this.upgraded = false;
        //cost
        this.cost = -2;
        this.upgradedCost = false;
        this.name = BASE_NAME;
        this.initializeTitle();
        rawDescription = ORIGINAL_DESCRIPTION;
        initializeDescription();

        exhaust = false;
        exhaustOnUseOnce = false;
    }

    @Override
    public boolean canPlay(AbstractCard card) {
        if (card == this) {
            return upgraded;
        }
        return true;
    }

    @Override
    public void onReceiveDamageWhileInHand(DamageInfo info, int damageAmount) {
        if (!upgraded && info.type == DamageInfo.DamageType.HP_LOSS) {
            triggerUpgrade();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {

        if (upgraded) {
            this.addToBottom(
                    new GainBlockAction(p, block)
            );

            if (timesUpgraded == 1) {
                triggerDowngrade();
            }
        }
    }

    private void triggerDowngrade() {
        downgrade();
        for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if (c.cardID.equals(this.cardID) && c.timesUpgraded == 1) {
                BloodPalimpsest m_bp = (BloodPalimpsest)c;
                m_bp.downgrade();
                break;
            }
        }
    }

    private void triggerUpgrade() {
        this.upgrade();
        this.superFlash();
        this.applyPowers();
        this.addToBottom(
                new DiscardSpecificCardAction(this)
        );
        for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if (c.cardID.equals(this.cardID) && c.timesUpgraded == 0) {
                BloodPalimpsest m_bp = (BloodPalimpsest)c;
                m_bp.upgrade();
                break;
            }
        }
    }



    @Override
    public void triggerOnExhaust() {
        if (timesUpgraded == 1) {
            triggerDowngrade();
        }
    }
}
