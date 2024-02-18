package me.tammon.minecraftsmashheroes;

import me.tammon.minecraftsmashheroes.Commands.*;
import me.tammon.minecraftsmashheroes.Events.GeneralCluck.BlasterUseEvent;
import me.tammon.minecraftsmashheroes.Events.Karakot.KarakotHandUse;
import me.tammon.minecraftsmashheroes.Events.Shoop.LazerUseEvent;
import me.tammon.minecraftsmashheroes.Events.Skullfire.GunUseEvent;
import me.tammon.minecraftsmashheroes.Events.Tinman.ShooterUseEvent;
import me.tammon.minecraftsmashheroes.Features.ItemDisplayHoming;
import me.tammon.minecraftsmashheroes.Heroes.CustomHeroes.GeneralCluck.Blaster;
import me.tammon.minecraftsmashheroes.Heroes.CustomHeroes.Karakot.Kamehameha;
import me.tammon.minecraftsmashheroes.Heroes.CustomHeroes.Shoop.ShoopShooter;
import me.tammon.minecraftsmashheroes.Heroes.CustomHeroes.Skullfire.SkullfireGun;
import me.tammon.minecraftsmashheroes.Heroes.CustomHeroes.Tinman.TinmanShooter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class MinecraftSmashHeroes extends JavaPlugin {

    public static MinecraftSmashHeroes PLUGIN;
    public static ItemDisplayHoming HOMING = new ItemDisplayHoming(new ItemStack(Material.SUNFLOWER), entity -> {
        Bukkit.broadcastMessage("hit: " + entity.getName());
        return true;
    }, block -> {
        Bukkit.broadcastMessage("hit: " + block);
        return true;
    });
//    public static EntityHoming HOMING = new EntityHoming(entity -> {
//        Bukkit.broadcastMessage("hit: " + entity.getName());
//        return true;
//    }, block -> {
//        Bukkit.broadcastMessage("hit: " + block);
//        return true;
//    });
//
//    static {
//        HOMING.setHomingEntityDefaultType(EntityType.ARROW);
//    }

    @Override
    public void onEnable() {
        PLUGIN = this;

        // Plugin startup logic
        System.out.println("[MinecraftSmashHeroes] Starting plugin.");
        Objects.requireNonNull(this.getCommand("setHero")).setExecutor(new CommandSetHero());
        Objects.requireNonNull(this.getCommand("getCustomItem")).setExecutor(new CommandGetCustomItem());
        Objects.requireNonNull(this.getCommand("summonVehicle")).setExecutor(new CommandSummonVehicle());
        Objects.requireNonNull(this.getCommand("drawLine")).setExecutor(new CommandDrawLine());
        Objects.requireNonNull(this.getCommand("dash")).setExecutor(new CommandDash());

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
