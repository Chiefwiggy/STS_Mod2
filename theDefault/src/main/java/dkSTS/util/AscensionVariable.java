package dkSTS.util;

import com.badlogic.gdx.scenes.scene2d.ui.Tree;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class AscensionVariable<T> {

    private final NavigableMap<Integer, T> varMap;

    public AscensionVariable() {
        varMap = new TreeMap<>();
        varMap.ceilingEntry(20);
        varMap.floorEntry(0);
    }

    public T get() {
        // Check if the exact ascensionLevel is present
        T value = varMap.get(AbstractDungeon.ascensionLevel);

        // If not, get the next lowest entry
        if (value == null) {
            Map.Entry<Integer, T> lowerEntry = varMap.lowerEntry(AbstractDungeon.ascensionLevel);
            if (lowerEntry != null) {
                value = lowerEntry.getValue();
            }
        }

        return value;
    }

    protected void add(T data, int ascension) {
        varMap.put(ascension, data);
    }
}
