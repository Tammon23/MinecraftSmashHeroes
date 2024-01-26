package me.tammon.minecraftsmashheroes.Heroes.BaseAbilities;

import me.tammon.minecraftsmashheroes.Heroes.Ability;

/** Ranged ability with no delay */
public abstract class InstantRanged extends Ability {
    public double attack_damage;
    public InstantRanged(String lore_title, String lore_description) {
        super(lore_title, lore_description);
    }
}
