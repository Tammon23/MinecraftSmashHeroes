package me.tammon.minecraftsmashheroes.Heroes.CustomHeroes.Shoop;

import me.tammon.minecraftsmashheroes.Features.BaseAbilities.DelayedRanged;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ShoopShooter implements DelayedRanged {
    private static ItemStack shoopShooter;
    private static int maxBulletLife;
    private static int speed;

    public ShoopShooter(int maxBulletLife, int speed) {
        this(maxBulletLife, speed, false);

    }

    public ShoopShooter(int maxBulletLife, int speed, boolean useMasterskin) {
        ShoopShooter.maxBulletLife = maxBulletLife;
        ShoopShooter.speed = speed;

        ShoopShooter.createGun(useMasterskin);
    }

    private static void createGun(boolean useMasterskin){
        ItemStack item = new ItemStack(Material.BEEF);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.AQUA + "Shoops's Lazer");

        List<String> lore = new ArrayList<>();
        lore.add("This is example lore");
        meta.setLore(lore);

        item.setItemMeta(meta);

        shoopShooter = item;
    }

    public static int getSpeed() {
        return speed != 0 ? speed : 1;
    }

    public static int getMaxBulletLife() {
        return maxBulletLife != 0 ? maxBulletLife : 100;
    }

    public static ItemStack getShoopShooter() {
        return shoopShooter;
    }
}
