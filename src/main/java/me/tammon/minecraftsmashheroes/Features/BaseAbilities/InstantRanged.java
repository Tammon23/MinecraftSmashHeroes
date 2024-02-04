package me.tammon.minecraftsmashheroes.Features.BaseAbilities;

import me.tammon.minecraftsmashheroes.Heroes.Ability;

/** Ranged ability with no delay */
public abstract class InstantRanged extends Ability {
    public double attackDamage;
    public InstantRanged(String loreTitle, String loreDescription) {
        super(loreTitle, loreDescription);
    }
}
