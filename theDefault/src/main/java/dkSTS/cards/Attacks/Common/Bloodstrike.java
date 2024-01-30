package dkSTS.cards.Attacks.Common;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.cards.Abstracts.AbstractWildCard;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;
import dkSTS.cards.Helpers.DamageActionBuilder;

public class Bloodstrike extends AbstractWildCard {
    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(Bloodstrike.class)
            .img("Bloodstrike.png")
            .target(CardTarget.SELF_AND_ENEMY)
            .type(CardType.ATTACK)
            .rarity(CardRarity.COMMON)
            .build();

    private static final int DAMAGE_ENEMY = 16;
    private static final int DAMAGE_ENEMY_UPGRADE = 4;
    private static final int SELF_DAMAGE = 3;

    public Bloodstrike() {
        super(data);

        baseDamage = DAMAGE_ENEMY;
        magicNumber = baseMagicNumber = SELF_DAMAGE;

        this.tags.add(CardTags.STRIKE);
    }

    @Override
    protected void UpgradeParameters() {
        upgradeDamage(DAMAGE_ENEMY_UPGRADE);
    }

    @Override
    public void wildUse(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        new DamageActionBuilder()
                .damage(damage)
                .target(abstractMonster)
                .damageType(DamageInfo.DamageType.NORMAL)
                .addToBottom();

        new DamageActionBuilder()
                .damage(magicNumber)
                .target(abstractPlayer)
                .damageType(DamageInfo.DamageType.HP_LOSS)
                .addToBottom();
    }
}
