package dkSTS.util;

import basemod.BaseMod;
import basemod.abstracts.CustomSavable;
import com.megacrit.cardcrawl.cards.purple.Brilliance;
import com.megacrit.cardcrawl.powers.watcher.MantraPower;

import java.util.ArrayList;

public class EvasionCounter implements CustomSavable<Integer> {

    private static EvasionCounter instance;
    private static EvasionCounter GetInstance() {
        if (instance == null) {
            instance = new EvasionCounter();
        }
        return instance;
    }

    private int currentEvasion;
    private final ArrayList<EvasionSubscriber> subs;
    private static final int STARTING_EVASION = 6;

    private EvasionCounter() {
        currentEvasion = 0;
        subs = new ArrayList<>();
    }

    public static void RegisterSelf() {
        BaseMod.addSaveField("evasion", GetInstance());
    }

    public static void StartRun() {
        GetInstance()._StartRun();
    }

    private void _StartRun() {
        currentEvasion = STARTING_EVASION;
    }

    public static int GetCurrentEvasion() {
        return GetInstance()._GetCurrentEvasion();
    }

    private int _GetCurrentEvasion() {
        return currentEvasion;
    }

    public static void ResetCounter() {
        GetInstance()._ResetCounter();
    }

    private void _ResetCounter() {
        currentEvasion = 0;
    }

    public static void IncrementCounter() {
        GetInstance()._IncrementCounter();
    }

    private void _IncrementCounter() {
        currentEvasion++;
        _NotifySubscribers();
    }

    public static void RegisterSubscriber(EvasionSubscriber p) {
        GetInstance()._RegisterSubscriber(p);
    }

    private void _RegisterSubscriber(EvasionSubscriber p) {
        subs.add(p);
    }

    public static void DeregisterSubscriber(EvasionSubscriber p) {
        GetInstance()._DeregisterSubscriber(p);
    }

    private void _DeregisterSubscriber(EvasionSubscriber p) {
        subs.remove(p);
    }

    private void _NotifySubscribers() {
        for (EvasionSubscriber sub : subs) {
            sub.receiveEvasionModified(currentEvasion);
        }
    }

    @Override
    public Integer onSave() {
        return currentEvasion;
    }

    @Override
    public void onLoad(Integer loadedEvasion) {
        currentEvasion = loadedEvasion;
    }
}
