package me.tammon.minecraftsmashheroes.Features.BaseAbilities;

import me.tammon.minecraftsmashheroes.Heroes.Ability;

/** A ranged ability that moves at a given speed */
public abstract class DelayedRanged extends Ability {
    public double attackDamage;
    public DelayedRanged(String loreTitle, String loreDescription) {
        super(loreTitle, loreDescription);
    }
}
