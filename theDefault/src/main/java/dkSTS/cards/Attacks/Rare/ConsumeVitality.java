package dkSTS.cards.Attacks.Rare;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dkSTS.actions.ConsumeVitalityAction;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.cards.Abstracts.AbstractDrainCard;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;

public class ConsumeVitality extends AbstractDrainCard {

    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(ConsumeVitality.class)
            .img("ConsumeVitality.png")
            .type(CardType.ATTACK)
            .rarity(CardRarity.RARE)
            .target(CardTarget.ENEMY)
            .build();

    private static final int DAMAGE = 11;
    private static final int DAMAGE_UPGRADE = 5;

    private static final int HEAL = 8;
    private static final int HEAL_UPGRADE = 2;



    public ConsumeVitality() {
        super(data);

        exhaust = exhaustOnUseOnce = true;
        baseDamage = DAMAGE;
        baseHeal = heal = HEAL;
    }

    @Override
    protected void UpgradeParameters() {
        upgradeDamage(DAMAGE_UPGRADE);
        upgradeHeal(HEAL_UPGRADE);
    }

    @Override
    public void postHungerUse(AbstractPlayer p, AbstractMonster m) {
        addToBottom(
                new ConsumeVitalityAction(m, new DamageInfo(p,damage), heal)
        );
    }


}
