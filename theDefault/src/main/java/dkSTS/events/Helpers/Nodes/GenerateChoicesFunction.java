package dkSTS.events.Helpers.Nodes;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.random.Random;

import java.util.ArrayList;

public interface GenerateChoicesFunction {
    public ArrayList<EventChoice> createChoices(AbstractPlayer p, Random eventRng);
}
