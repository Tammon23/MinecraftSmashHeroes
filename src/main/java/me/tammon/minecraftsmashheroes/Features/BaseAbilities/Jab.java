package me.tammon.minecraftsmashheroes.Features.BaseAbilities;

import me.tammon.minecraftsmashheroes.Heroes.Ability;
import org.bukkit.util.Vector;

public abstract class Jab extends Ability {

    protected double attack_damage;
    protected Vector knock_back;

    public Jab(String lore_title, String lore_description) {
        super(lore_title, lore_description);
    }
}
