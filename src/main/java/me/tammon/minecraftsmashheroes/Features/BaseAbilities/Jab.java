package me.tammon.minecraftsmashheroes.Features.BaseAbilities;

import me.tammon.minecraftsmashheroes.Heroes.Ability;
import org.bukkit.util.Vector;

public abstract class Jab extends Ability {

    protected double attackDamage;
    protected Vector knockBack;

    public Jab(String loreTitle, String loreDescription) {
        super(loreTitle, loreDescription);
    }
}
