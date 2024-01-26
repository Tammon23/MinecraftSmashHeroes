package me.tammon.minecraftsmashheroes;

import me.tammon.minecraftsmashheroes.Commands.CommandSetHero;
import org.bukkit.plugin.java.JavaPlugin;

public final class MinecraftSmashHeroes extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("[MinecraftSmashHeroes] Starting plugin.");

        this.getCommand("setHero").setExecutor(new CommandSetHero());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        System.out.println("[MinecraftSmashHeroes] Stopping plugin.");
    }
}
