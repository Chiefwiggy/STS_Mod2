//package dkSTS.cards_DEPRECATED;
//
//import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
//import com.megacrit.cardcrawl.cards.status.Dazed;
//import com.megacrit.cardcrawl.characters.AbstractPlayer;
//import com.megacrit.cardcrawl.monsters.AbstractMonster;
//import dkSTS.cards.Abstracts.AbstractBruxaCard;
//import dkSTS.cards.Helpers.BruxaCardData;
//import dkSTS.cards.Helpers.BruxaCardDataBuilder;
//import dkSTS.stances.DireWolfStance;
//
//public class BestialTransformation extends AbstractBruxaCard {
//    public static BruxaCardData data = new BruxaCardDataBuilder()
//            .id(BestialTransformation.class)
//            .img("Skill.png")
//            .type(CardType.SKILL)
//            .rarity(CardRarity.RARE)
//            .target(CardTarget.SELF)
//            .cost(3)
//            .build();
//
//    public BestialTransformation() {
//        super(data);
//
//        cardsToPreview = new Dazed();
//        exhaust = true;
//    }
//
//    @Override
//    protected void UpgradeParameters() {
//        upgradeBaseCost(2);
//    }
//
//    @Override
//    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
//        this.addToBottom(
//                new ChangeStanceAction(new DireWolfStance())
//        );
//    }
//}
