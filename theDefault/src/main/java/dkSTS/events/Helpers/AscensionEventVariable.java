package dkSTS.events.Helpers;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import dkSTS.util.AscensionVariable;

public class AscensionEventVariable<T> extends AscensionVariable<T> {

    public AscensionEventVariable() {
        super();
    }

    public AscensionEventVariable(T base, T a15) {
        this();
        this.add(base, 0);
        this.add(a15, 15);
    }

    public void setBase(T data) {
        this.add(data, 0);
    }

    public void setA15(T data) {
        this.add(data, 15);
    }


}
