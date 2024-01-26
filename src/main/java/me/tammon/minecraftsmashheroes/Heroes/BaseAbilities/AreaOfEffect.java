package me.tammon.minecraftsmashheroes.Heroes.BaseAbilities;

import me.tammon.minecraftsmashheroes.Heroes.Ability;

/** An ability that attacks multiple entities, centered around the caller */
public abstract class AreaOfEffect extends Ability {
    protected int max_effected_players = -1;
    public AreaOfEffect(String lore_title, String lore_description) {
        super(lore_title, lore_description);
    }
}
