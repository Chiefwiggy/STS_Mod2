package dkSTS.cards_DEPRECATED;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Dazed;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dkSTS.actions.RedrawCardAction;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;
import dkSTS.cards.Helpers.DamageActionBuilder;

public class Faint {
//    public static BruxaCardData data = new BruxaCardDataBuilder()
//            .id(Faint.class)
//            .img("Skill.png")
//            .rarity(CardRarity.SPECIAL)
//            .cost(0)
//            .target(CardTarget.SELF)
//            .type(CardType.SKILL)
//            .build();
//
//    private static final int DZ_REDRAW = 5;
//    private static final int DZ_REDRAW_UPGRADE = 5;
//    private static final int HP_LOSS = 8;
//    private static final int HP_LOSS_UPGRADE = -2;
//
//
//    public Faint() {
//        super(data);
//
//        exhaust = true;
//        baseMagicNumber = magicNumber = DZ_REDRAW;
//        __baseMagicThird = __magicThird = HP_LOSS;
//    }
//
//    @Override
//    protected void UpgradeParameters() {
//        upgradeMagicNumber(DZ_REDRAW_UPGRADE);
//        upgradeMagicThird(HP_LOSS_UPGRADE);
//    }
//
//    @Override
//    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
//
//        this.addToBottom(
//                new RedrawCardAction(p, magicNumber, false,
//                        new MakeTempCardInDiscardAction(new Dazed(), DZ_REDRAW))
//        );
//
//        if (upgraded) {
//            this.addToBottom(
//                    new MakeTempCardInDiscardAction(new Dazed(), DZ_REDRAW_UPGRADE)
//            );
//        }
//
//        new DamageActionBuilder()
//                .target(p)
//                .damage(__magicThird)
//                .damageType(DamageInfo.DamageType.HP_LOSS)
//                .addToBottom();
//    }
}
