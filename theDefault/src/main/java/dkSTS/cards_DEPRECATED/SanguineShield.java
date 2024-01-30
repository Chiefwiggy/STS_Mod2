//package dkSTS.cards_DEPRECATED;

//import com.badlogic.gdx.graphics.Color;
//import com.megacrit.cardcrawl.actions.common.GainBlockAction;
//import com.megacrit.cardcrawl.cards.AbstractCard;
//import com.megacrit.cardcrawl.cards.DamageInfo;
//import com.megacrit.cardcrawl.characters.AbstractPlayer;
//import com.megacrit.cardcrawl.monsters.AbstractMonster;
//import dkSTS.cards.Abstracts.AbstractBruxaCard;
//import dkSTS.cards.Helpers.ApplyPowerBuilder;
//import dkSTS.cards.Helpers.BruxaCardData;
//import dkSTS.cards.Helpers.BruxaCardDataBuilder;
//import dkSTS.cards.Helpers.DamageActionBuilder;
//import dkSTS.powers.BloodPower;
//
//public class SanguineShield extends AbstractBruxaCard {
//    public static BruxaCardData data = new BruxaCardDataBuilder()
//            .id(SanguineShield.class)
//            .img("Skill.png")
//            .target(CardTarget.SELF)
//            .cost(1)
//            .rarity(CardRarity.COMMON)
//            .type(CardType.SKILL)
//            .build();
//
//
//    private static final int HP_LOSS = 1;
//    private static final int BLOCK = 6;
//    private static final int BLOCK_UPGRADE = 2;
//
//
//
//    private static final int HUNGER = 1;
//    private static final int HUNGER_UPGRADE = 1;
//
//    public SanguineShield() {
//        super(data);
//
//        baseBlock = block = BLOCK;
//        baseMagicNumber = magicNumber = HUNGER;
//        __baseMagicThird = __magicThird = HP_LOSS;
//    }
//
//    @Override
//    protected void UpgradeParameters() {
//        upgradeBlock(BLOCK_UPGRADE);
//        upgradeMagicNumber(HUNGER_UPGRADE);
//    }
//
//    @Override
//    public void use(AbstractPlayer p, AbstractMonster m) {
//        new DamageActionBuilder()
//                .target(p)
//                .damage(__magicThird)
//                .damageType(DamageInfo.DamageType.HP_LOSS)
//                .addToBottom();
//
//        new ApplyPowerBuilder()
//                .power(BloodPower.class)
//                .amount(magicNumber)
//                .addToBottom();
//
//        this.addToBottom(
//                new GainBlockAction(p, block)
//        );
//        if (isPlayerBloodied()) {
//            this.addToBottom(
//                    new GainBlockAction(p, block)
//            );
//        }
//    }
//
//    @Override
//    public void triggerOnGlowCheck() {
//        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
//        if (isPlayerBloodied()) {
//            this.glowColor = Color.RED.cpy();
//        }
//    }
//}
