package me.tammon.minecraftsmashheroes.Features.BaseAbilities;

import me.tammon.minecraftsmashheroes.Heroes.Ability;

/** An ability that attacks multiple entities, centered around the caller */
public abstract class AreaOfEffect extends Ability {
    protected int maxEffectedPlayers = -1;
    public AreaOfEffect(String loreTitle, String loreDescription) {
        super(loreTitle, loreDescription);
    }
}
