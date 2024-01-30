package dkSTS.acts.Helpers;

import dkSTS.util.AscensionVariable;

public class AscensionActVariable<T> extends AscensionVariable<T> {

    public AscensionActVariable() {super();}

    public AscensionActVariable(T base, T a12) {
        this();
        this.add(base, 0);
        this.add(a12, 12);
    }

    public void setBase(T data) {
        this.add(data, 0);
    }
    public void setA12(T data) {
        this.add(data, 12);
    }
}
