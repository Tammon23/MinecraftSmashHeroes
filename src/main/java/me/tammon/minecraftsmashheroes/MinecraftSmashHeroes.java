package me.tammon.minecraftsmashheroes;

import me.tammon.minecraftsmashheroes.Commands.CommandDrawLine;
import me.tammon.minecraftsmashheroes.Commands.CommandGetCustomItem;
import me.tammon.minecraftsmashheroes.Commands.CommandSetHero;
import me.tammon.minecraftsmashheroes.Commands.CommandSummonVehicle;
import me.tammon.minecraftsmashheroes.Events.GeneralCluck.BlasterUseEvent;
import me.tammon.minecraftsmashheroes.Events.Karakot.KarakotHandUse;
import me.tammon.minecraftsmashheroes.Events.Shoop.LazerUseEvent;
import me.tammon.minecraftsmashheroes.Events.Skullfire.GunUseEvent;
import me.tammon.minecraftsmashheroes.Events.Tinman.ShooterUseEvent;
import me.tammon.minecraftsmashheroes.Heroes.CustomHeroes.GeneralCluck.Blaster;
import me.tammon.minecraftsmashheroes.Heroes.CustomHeroes.Karakot.Kamehameha;
import me.tammon.minecraftsmashheroes.Heroes.CustomHeroes.Shoop.ShoopShooter;
import me.tammon.minecraftsmashheroes.Heroes.CustomHeroes.Skullfire.SkullfireGun;
import me.tammon.minecraftsmashheroes.Heroes.CustomHeroes.Tinman.TinmanShooter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class MinecraftSmashHeroes extends JavaPlugin {

    public static MinecraftSmashHeroes PLUGIN;

    @Override
    public void onEnable() {
        PLUGIN = this;

        // Plugin startup logic
        System.out.println("[MinecraftSmashHeroes] Starting plugin.");
        this.getCommand("setHero").setExecutor(new CommandSetHero());
        this.getCommand("getCustomItem").setExecutor(new CommandGetCustomItem());
        this.getCommand("summonVehicle").setExecutor(new CommandSummonVehicle());
        this.getCommand("drawLine").setExecutor(new CommandDrawLine());

        SkullfireGun skullfireGun = new SkullfireGun();
        TinmanShooter tinmanShooter = new TinmanShooter(100, 1);
        ShoopShooter shoopShooter = new ShoopShooter(100, 1);
        Kamehameha kamehameha = new Kamehameha(100, 1);
        Blaster blaster = new Blaster(100, 1);

        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new GunUseEvent(), this);
        pluginManager.registerEvents(new ShooterUseEvent(), this);
        pluginManager.registerEvents(new LazerUseEvent(), this);
        pluginManager.registerEvents(new KarakotHandUse(), this);
        pluginManager.registerEvents(new BlasterUseEvent(), this);


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        System.out.println("[MinecraftSmashHeroes] Stopping plugin.");
    }
}
