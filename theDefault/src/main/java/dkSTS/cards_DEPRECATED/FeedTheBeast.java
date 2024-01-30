//package dkSTS.cards_DEPRECATED;
//
//import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
//import com.megacrit.cardcrawl.characters.AbstractPlayer;
//import com.megacrit.cardcrawl.monsters.AbstractMonster;
//import dkSTS.actions.DevourWolvesAction;
//import dkSTS.cards.Abstracts.AbstractBruxaCard;
//import dkSTS.cards.Helpers.BruxaCardData;
//import dkSTS.cards.Helpers.BruxaCardDataBuilder;
//import dkSTS.cards.Helpers.DamageActionBuilder;
//import dkSTS.powers.WolfPower;
//import dkSTS.stances.DireWolfStance;
//
//public class FeedTheBeast extends AbstractBruxaCard {
//    public static BruxaCardData data = new BruxaCardDataBuilder()
//            .id(FeedTheBeast.class)
//            .img("Attack.png")
//            .rarity(CardRarity.RARE)
//            .target(CardTarget.ENEMY)
//            .cost(2)
//            .type(CardType.ATTACK)
//            .build();
//
//
//    private static final int DAMAGE = 10;
//    private static final int DAMAGE_UPGRADE = 4;
//
//    private static final int WOVLES_NEEDED = 10;
//    private static final int WOLVES_UPGRADE = -2;
//
//
//    public FeedTheBeast() {
//        super(data);
//
//        baseDamage = damage = DAMAGE;
//        baseMagicNumber = magicNumber = WOVLES_NEEDED;
//
//        exhaust = true;
//    }
//
//    @Override
//    protected void UpgradeParameters() {
//        upgradeDamage(DAMAGE_UPGRADE);
//        upgradeMagicNumber(WOLVES_UPGRADE);
//    }
//
//    @Override
//    public void use(AbstractPlayer p, AbstractMonster m) {
//
//
//        if (p.hasPower(WolfPower.data.POWER_ID)) {
//            if (p.getPower(WolfPower.data.POWER_ID).amount >= magicNumber) {
//                this.addToBottom(
//                        new ChangeStanceAction(new DireWolfStance())
//                );
//            }
//        }
//        new DamageActionBuilder()
//                .damage(damage)
//                .target(m)
//                .addToBottom();
//        this.addToBottom(
//                new DevourWolvesAction()
//        );
//
//    }
//}
