package dkSTS.cards.Skills.Starter;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import dkSTS.cards.Abstracts.AbstractBruxaCard;
import dkSTS.cards.Helpers.BruxaCardData;
import dkSTS.cards.Helpers.BruxaCardDataBuilder;

public class Defend extends AbstractBruxaCard {

    public static BruxaCardData data = new BruxaCardDataBuilder()
            .id(Defend.class)
            .img("Defend.png")
            .rarity(CardRarity.BASIC)
            .type(CardType.SKILL)
            .target(CardTarget.SELF)
            .build();

    private static final int BLOCK = 5;
    private static final int BLOCK_UPGRADE = 3;

    public Defend() {
        super(data);

        baseBlock = BLOCK;

        AddTag(CardTags.STARTER_DEFEND);
    }

    @Override
    protected void UpgradeParameters() {
        upgradeBlock(BLOCK_UPGRADE);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBottom(
                new GainBlockAction(abstractPlayer, abstractPlayer, block)
        );
    }
}
