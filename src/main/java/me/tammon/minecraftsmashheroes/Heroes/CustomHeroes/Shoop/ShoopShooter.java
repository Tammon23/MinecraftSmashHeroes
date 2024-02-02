package me.tammon.minecraftsmashheroes.Heroes.CustomHeroes.Shoop;

import me.tammon.minecraftsmashheroes.Features.BaseAbilities.DelayedRanged;
import me.tammon.minecraftsmashheroes.Heroes.CustomHeroes.Tinman.TinmanShooter;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ShoopShooter extends DelayedRanged {
    private static ItemStack shoop_shooter;
    private static int max_bullet_life;
    private static int speed;

    public ShoopShooter(int max_bullet_life,  int speed) {
        this(max_bullet_life, speed, false);

    }

    public ShoopShooter(int max_bullet_life, int speed, boolean use_masterskin) {
        super("example title", "example description");

        ShoopShooter.max_bullet_life = max_bullet_life;
        ShoopShooter.speed = speed;

        ShoopShooter.createGun(use_masterskin);
    }

    private static void createGun(boolean is_masterskinned){
        ItemStack item = new ItemStack(Material.BEEF);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.AQUA + "Shoops's Lazer");

        List<String> lore = new ArrayList<>();
        lore.add("This is example lore");
        meta.setLore(lore);

        item.setItemMeta(meta);

        shoop_shooter = item;
    }

    public static int getSpeed() {
        return speed != 0 ? speed : 1;
    }

    public static int getMax_bullet_life() {
        return max_bullet_life != 0 ? max_bullet_life : 100;
    }

    public static ItemStack getShoop_shooter() {
        return shoop_shooter;
    }
}