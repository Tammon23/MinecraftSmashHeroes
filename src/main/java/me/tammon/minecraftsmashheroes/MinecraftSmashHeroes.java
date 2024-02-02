package me.tammon.minecraftsmashheroes;

import me.tammon.minecraftsmashheroes.Commands.CommandGetCustomItem;
import me.tammon.minecraftsmashheroes.Commands.CommandSetHero;
import me.tammon.minecraftsmashheroes.Commands.CommandSummonVehicle;
import me.tammon.minecraftsmashheroes.Events.Skullfire.GunUseEvent;
import me.tammon.minecraftsmashheroes.Events.Tinman.ShooterUseEvent;
import me.tammon.minecraftsmashheroes.Heroes.CustomHeroes.Skullfire.SkullfireGun;
import me.tammon.minecraftsmashheroes.Heroes.CustomHeroes.Tinman.TinmanShooter;
import org.bukkit.plugin.java.JavaPlugin;

public final class MinecraftSmashHeroes extends JavaPlugin {

    public static MinecraftSmashHeroes PLUGIN;

    @Override
    public void onEnable() {
        PLUGIN = this;

        // Plugin startup logic
        System.out.println("[MinecraftSmashHeroes] Starting plugin.");
        SkullfireGun skullfireGun = new SkullfireGun();
        TinmanShooter tinmanShooter = new TinmanShooter(100, 1);

        this.getCommand("setHero").setExecutor(new CommandSetHero());
        this.getCommand("getCustomItem").setExecutor(new CommandGetCustomItem());
        this.getCommand("summonVehicle").setExecutor(new CommandSummonVehicle());

        getServer().getPluginManager().registerEvents(new GunUseEvent(), this);
        getServer().getPluginManager().registerEvents(new ShooterUseEvent(), this);


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        System.out.println("[MinecraftSmashHeroes] Stopping plugin.");
    }
}
