package dkSTS.cards.Helpers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DamageActionBuilder {

    private AbstractCreature _target;
    private boolean _isTargetAll;
    private boolean _isTargetRandom;
    private final AbstractPlayer _source;
    private int _damage;
    private DamageInfo.DamageType _type;
    private AbstractGameAction.AttackEffect _effect;

    private int _times;
    private int[] _multiTarget;

    public DamageActionBuilder() {
        _target = null;
        _source = AbstractDungeon.player;
        _damage = 0;
        _type = DamageInfo.DamageType.NORMAL;
        _effect = AbstractGameAction.AttackEffect.NONE;
        _isTargetAll = false;
        _isTargetRandom = false;
        _times = 1;
        _multiTarget = null;
    }

    public DamageActionBuilder target(AbstractCreature m) {
        this._target = m;
        return this;
    }

    public DamageActionBuilder targetAll(final int[] targetMatrix) {
        this._isTargetAll = true;
        this._multiTarget = targetMatrix;
        return this;
    }

    public DamageActionBuilder targetRandom() {
        this._isTargetRandom = true;
        return this;
    }

    public DamageActionBuilder repeat(final int amount) {
        this._times = amount;
        return this;
    }

    public DamageActionBuilder damage(int damage) {
        this._damage = damage;
        return this;
    }

    public DamageActionBuilder damageType(DamageInfo.DamageType type) {
        this._type = type;
        return this;
    }

    public DamageActionBuilder animation(AbstractGameAction.AttackEffect effect) {
        this._effect = effect;
        return this;
    }

    public void addToBottom() {
        for (int i = 0; i < _times; ++i) {
            AbstractDungeon.actionManager.addToBottom(
                    createGameAction()
            );
        }
    }

    public void addToTop() {
        for (int i = 0; i < _times; ++i) {
            AbstractDungeon.actionManager.addToTop(
                    createGameAction()
            );
        }
    }

    public void addToEvent() {
        for (int i = 0; i < _times; ++i) {
            _target.damage(new DamageInfo(_target, _damage, DamageInfo.DamageType.HP_LOSS));
        }
    }

    private AbstractGameAction createGameAction() {

        if (_isTargetAll) {
            return new DamageAllEnemiesAction(_source, _multiTarget, _type, _effect);
        } else if (_isTargetRandom) {
            return new DamageRandomEnemyAction(new DamageInfo(_source, _damage, _type), _effect);
        } else {
            return new DamageAction(_target, new DamageInfo(_source, _damage, _type), _effect);
        }

    }
}
