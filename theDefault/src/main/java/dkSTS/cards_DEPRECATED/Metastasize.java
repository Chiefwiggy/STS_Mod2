//package dkSTS.cards_DEPRECATED;
//
//import com.badlogic.gdx.math.MathUtils;
//import com.megacrit.cardcrawl.cards.DamageInfo;
//import com.megacrit.cardcrawl.characters.AbstractPlayer;
//import com.megacrit.cardcrawl.monsters.AbstractMonster;
//import dkSTS.cards.Abstracts.AbstractBruxaCard;
//import dkSTS.cards.Helpers.BruxaCardData;
//import dkSTS.cards.Helpers.BruxaCardDataBuilder;
//import dkSTS.cards.Helpers.DamageActionBuilder;
//
//public class Metastasize extends AbstractBruxaCard {
//    public static BruxaCardData data = new BruxaCardDataBuilder()
//            .id(Metastasize.class)
//            .img("Metastasize.png")
//            .rarity(CardRarity.RARE)
//            .type(CardType.SKILL)
//            .target(CardTarget.SELF)
//            .cost(1)
//            .build();
//
//    private static final int HP_GAIN = 3;
//    private static final int HP_UPGRADE = 1;
//
//    public Metastasize() {
//        super(data);
//
//        exhaust = exhaustOnUseOnce = true;
//        selfRetain = true;
//        baseMagicNumber = magicNumber = HP_GAIN;
//    }
//
//    @Override
//    protected void UpgradeParameters() {
//        upgradeMagicNumber(HP_UPGRADE);
//    }
//
//    @Override
//    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
//
//        int halb = MathUtils.round(p.currentHealth * 0.5F);
//
//        new DamageActionBuilder()
//                .target(p)
//                .damageType(DamageInfo.DamageType.HP_LOSS)
//                .damage(halb)
//                .addToBottom();
//
//        if (!p.isDying && !p.isDead) {
//            p.currentHealth -= magicNumber;
//            p.increaseMaxHp(magicNumber, true);
//        }
//
//
//    }
//}
