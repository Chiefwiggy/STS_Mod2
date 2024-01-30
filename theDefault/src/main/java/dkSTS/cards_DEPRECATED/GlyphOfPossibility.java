//package dkSTS.cards_DEPRECATED;
//
//import com.megacrit.cardcrawl.characters.AbstractPlayer;
//import com.megacrit.cardcrawl.monsters.AbstractMonster;
//import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
//import dkSTS.cards.Abstracts.AbstractBruxaCard;
//import dkSTS.cards.Helpers.ApplyPowerBuilder;
//import dkSTS.cards.Helpers.BruxaCardData;
//import dkSTS.cards.Helpers.BruxaCardDataBuilder;
//
//public class GlyphOfPossibility extends AbstractBruxaCard {
//    public static BruxaCardData data = new BruxaCardDataBuilder()
//            .id(GlyphOfPossibility.class)
//            .img("GlyphOfPossibility.png")
//            .rarity(CardRarity.COMMON)
//            .type(CardType.SKILL)
//            .cost(1)
//            .target(CardTarget.SELF)
//            .build();
//
//    private static final int CARDS_TO_DRAW = 3;
//    private static final int CARDS_UPGRADE = 1;
//
//    public GlyphOfPossibility() {
//        super(data);
//
//        baseMagicNumber = magicNumber = CARDS_TO_DRAW;
//        selfRetain = true;
//    }
//
//    @Override
//    protected void UpgradeParameters() {
//        upgradeMagicNumber(CARDS_UPGRADE);
//    }
//
//    @Override
//    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
//        new ApplyPowerBuilder()
//                .power(DrawCardNextTurnPower.class)
//                .amount(magicNumber)
//                .addToBottom();
//    }
//}
