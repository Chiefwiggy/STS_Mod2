//package dkSTS.cards_DEPRECATED;
//
//import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
//import com.megacrit.cardcrawl.cards.AbstractCard;
//import com.megacrit.cardcrawl.characters.AbstractPlayer;
//import com.megacrit.cardcrawl.monsters.AbstractMonster;
//import dkSTS.cards.Abstracts.AbstractBruxaCard;
//import dkSTS.cards.Helpers.BruxaCardData;
//import dkSTS.cards.Helpers.BruxaCardDataBuilder;
//import dkSTS.cards.Helpers.DamageActionBuilder;
//import dkSTS.powers.FrostPower;
//
//public class Shatter extends AbstractBruxaCard {
//    public static BruxaCardData data = new BruxaCardDataBuilder()
//            .id(Shatter.class)
//            .img("Shatter.png")
//            .target(AbstractCard.CardTarget.ENEMY)
//            .rarity(AbstractCard.CardRarity.UNCOMMON)
//            .type(AbstractCard.CardType.ATTACK)
//            .cost(2)
//            .build();
//
//    private static final int BASE_DAMAGE = 9;
//    private static final int DAMAGE_PER = 4;
//    private static final int DAMAGE_PER_UPGRADE = 2;
//
//    public Shatter() {
//        super(data);
//
//        baseDamage = BASE_DAMAGE;
//        baseMagicNumber = magicNumber = DAMAGE_PER;
//    }
//
//    @Override
//    protected void UpgradeParameters() {
//        upgradeMagicNumber(DAMAGE_PER_UPGRADE);
//    }
//
//    @Override
//    public void calculateCardDamage(AbstractMonster mo) {
//        super.calculateCardDamage(mo);
//        if (mo.hasPower(FrostPower.data.POWER_ID)) {
//            damage += magicNumber * mo.getPower(FrostPower.data.POWER_ID).amount;
//        }
//        this.isDamageModified = this.damage != this.baseDamage;
//    }
//
//    @Override
//    public void use(AbstractPlayer p, AbstractMonster m) {
//        new DamageActionBuilder()
//                .damage(damage)
//                .target(m)
//                .addToBottom();
//
//        this.addToBottom(
//                new RemoveSpecificPowerAction(m, p, FrostPower.data.POWER_ID)
//        );
//    }
//}
