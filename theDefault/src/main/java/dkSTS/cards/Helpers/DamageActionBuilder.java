package dkSTS.cards.Helpers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DamageActionBuilder {

    private AbstractMonster _target;
    private AbstractPlayer _source;
    private int _damage;
    private DamageInfo.DamageType _type;
    private AbstractGameAction.AttackEffect _effect;

    public DamageActionBuilder() {
        _target = null;
        _source = AbstractDungeon.player;
        _damage = 0;
        _type = DamageInfo.DamageType.NORMAL;
        _effect = AbstractGameAction.AttackEffect.NONE;
    }

    public DamageActionBuilder target(AbstractMonster m) {
        this._target = m;
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


    public DamageAction build() {
        return new DamageAction(_target, new DamageInfo(_source, _damage, _type), _effect);
    }
}
