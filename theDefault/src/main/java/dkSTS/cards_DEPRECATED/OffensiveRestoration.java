package dkSTS.cards_DEPRECATED;

import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;
import dkSTS.cards.Helpers.DamageActionBuilder;

public class OffensiveRestoration  {
//    public static BruxaCardData data = new BruxaCardDataBuilder()
//            .id(OffensiveRestoration.class)
//            .img("Attack.png")
//            .type(CardType.ATTACK)
//            .target(CardTarget.ALL_ENEMY)
//            .cost(2)
//            .rarity(CardRarity.SPECIAL)
//            .build();
//
//    private static final int EXHAUST_PER = 4;
//
//    public OffensiveRestoration() {
//        super(data);
//
//        baseMagicNumber = magicNumber = EXHAUST_PER;
//        baseDamage = damage = 0;
//
//        isMultiDamage = true;
//    }
//
//
//    @Override
//    protected void UpgradeParameters() {
//        UpgradeDescription();
//    }
//
//    @Override
//    public void applyPowers() {
//        super.applyPowers();
//        int statuses = 0;
//        for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
//            if (c.type == CardType.STATUS) {
//                statuses++;
//            }
//        }
//        if (upgraded) {
//            for (AbstractCard c : AbstractDungeon.player.hand.group) {
//                if (c.type == CardType.STATUS) {
//                    statuses++;
//                }
//            }
//        }
//        damage += statuses * magicNumber;
//
//        this.isDamageModified = this.damage != this.baseDamage;
//    }
//
//    @Override
//    public void calculateCardDamage(AbstractMonster mo) {
//
//        int statuses = 0;
//        for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
//            if (c.type == CardType.STATUS) {
//                statuses++;
//            }
//        }
//        if (upgraded) {
//            for (AbstractCard c : AbstractDungeon.player.hand.group) {
//                if (c.type == CardType.STATUS) {
//                    statuses++;
//                }
//            }
//        }
//        int originalBaseDamage = baseDamage;
//        baseDamage += statuses * magicNumber;
//
//        super.calculateCardDamage(mo);
//        damage = baseDamage;
//        baseDamage = originalBaseDamage;
//
//        this.isDamageModified = this.damage != this.baseDamage;
//    }
//
//    @Override
//    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
//
//        new DamageActionBuilder()
//                .damage(damage)
//                .targetAll(this.multiDamage)
//                .addToTop();
//
//        for (AbstractCard c : p.discardPile.group) {
//            if (c.type == CardType.STATUS) {
//                this.addToBottom(
//                        new ExhaustSpecificCardAction(c, p.discardPile)
//                );
//            }
//        }
//
//        if (upgraded) {
//            for (AbstractCard c : p.hand.group) {
//                if (c.type == CardType.STATUS) {
//                    this.addToBottom(
//                            new ExhaustSpecificCardAction(c, p.hand)
//                    );
//                }
//            }
//        }
//    }
}
