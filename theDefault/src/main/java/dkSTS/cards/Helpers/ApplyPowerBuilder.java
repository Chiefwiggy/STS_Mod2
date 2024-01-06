package dkSTS.cards.Helpers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;

public class ApplyPowerBuilder {

    private Class<? extends AbstractPower> powerTemplate;
    private AbstractCreature target;
    private AbstractCreature source;
    private int powerAmount;
    private boolean doesTargetAllEnemies;
    private boolean isSourceMonster;

    public ApplyPowerBuilder() {
        powerTemplate = null;
        target = AbstractDungeon.player;
        doesTargetAllEnemies = false;
        source = AbstractDungeon.player;
        powerAmount = 1;
        isSourceMonster =  false;
    }

    public ApplyPowerBuilder power(Class<? extends AbstractPower> power) {
        this.powerTemplate = power;
        return this;
    }

    //by default targets player, so unnecessary to specify
    public ApplyPowerBuilder target(final AbstractMonster mon) {
        target = mon;
        return this;
    }


    // by default source to player, so unnecessary to specify
    public ApplyPowerBuilder source(final AbstractMonster mon) {
        source = mon;
        isSourceMonster = true;
        return this;
    }

    public ApplyPowerBuilder targetAll() {
        doesTargetAllEnemies = true;
        return this;
    }

    public ApplyPowerBuilder amount(final int amount) {
        this.powerAmount = amount;
        return this;
    }

    public void addToBottom() {
        ArrayList<AbstractGameAction> actions = createActions();
        for (AbstractGameAction action : actions) {
            AbstractDungeon.actionManager.addToBottom(action);
        }
    }

    public void addToTop() {
        ArrayList<AbstractGameAction> actions = createActions();
        for (AbstractGameAction action : actions) {
            AbstractDungeon.actionManager.addToTop(action);
        }

    }

    private ArrayList<AbstractGameAction> createActions() {
        ArrayList<AbstractGameAction> retList = new ArrayList<>();
        if (doesTargetAllEnemies) {
            for (AbstractMonster mon : AbstractDungeon.getCurrRoom().monsters.monsters) {
                if (!mon.isDeadOrEscaped()) {
                    retList.add(new ApplyPowerAction(mon, source, createPower(mon)));
                }
            }
        } else {
            retList.add(new ApplyPowerAction(target, source, createPower(target)));
        }
        return retList;
    }

    private AbstractPower createPower(AbstractCreature target) {
        return new ConstructorBuilder<>(powerTemplate)
                .addConstruct(AbstractCreature.class, AbstractCreature.class, int.class)
                .addConstructParams(target, source, powerAmount)
                .addConstruct(AbstractCreature.class, int.class)
                .addConstructParams(target, powerAmount)
                .addConstruct(AbstractCreature.class, AbstractCreature.class)
                .addConstructParams(target, source)
                .addConstruct(AbstractCreature.class, int.class, boolean.class)
                .addConstructParams(target, powerAmount, isSourceMonster)
                .build();
    }



}
