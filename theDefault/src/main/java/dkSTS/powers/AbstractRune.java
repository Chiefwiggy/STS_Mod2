package dkSTS.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import dkSTS.powers.Helpers.PowerData;

public abstract class AbstractRune extends AbstractInstancedPower {

    protected int magicNumber;

    public AbstractRune(PowerData dataBuilder, AbstractCreature owner, AbstractCreature source, final int magic) {
        super(dataBuilder, owner, source, 2);
        this.magicNumber = magic;
    }

    @Override
    public void onRemove() {
        whenRuneResolves();
        super.onRemove();
    }

    public abstract void whenRuneResolves();
}
