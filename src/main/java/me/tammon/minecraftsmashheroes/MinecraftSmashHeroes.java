package me.tammon.minecraftsmashheroes;

import me.tammon.minecraftsmashheroes.Commands.CommandGetCustomItem;
import me.tammon.minecraftsmashheroes.Commands.CommandSetHero;
import me.tammon.minecraftsmashheroes.Events.Skullfire.*;
import me.tammon.minecraftsmashheroes.Features.CustomAbilities.Gun;
import org.bukkit.plugin.java.JavaPlugin;

public final class MinecraftSmashHeroes extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("[MinecraftSmashHeroes] Starting plugin.");
        Gun skullfire_gun = new Gun();

        this.getCommand("setHero").setExecutor(new CommandSetHero());
        this.getCommand("getCustomItem").setExecutor(new CommandGetCustomItem());

        getServer().getPluginManager().registerEvents(new GunUseEvent(), this);


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        System.out.println("[MinecraftSmashHeroes] Stopping plugin.");
    }
}
