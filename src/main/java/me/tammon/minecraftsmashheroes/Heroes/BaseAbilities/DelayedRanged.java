package me.tammon.minecraftsmashheroes.Heroes.BaseAbilities;

import me.tammon.minecraftsmashheroes.Heroes.Ability;

/** A ranged ability that moves at a given speed */
public abstract class DelayedRanged extends Ability {
    public double attack_damage;
    public DelayedRanged(String lore_title, String lore_description) {
        super(lore_title, lore_description);
    }

    public abstract void move();
    public abstract void onHit();
}
