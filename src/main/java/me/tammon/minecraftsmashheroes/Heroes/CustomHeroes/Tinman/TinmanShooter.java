package me.tammon.minecraftsmashheroes.Heroes.CustomHeroes.Tinman;

import me.tammon.minecraftsmashheroes.Features.BaseAbilities.DelayedRanged;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class TinmanShooter extends DelayedRanged{
    private static ItemStack tinmanShooter;
    private static int maxBulletLife;
    private static int speed;

    public TinmanShooter(int maxBulletLife, int speed) {
        this(maxBulletLife, speed, false);
    }


    public TinmanShooter(int maxBulletLife, int speed, boolean useMasterskin) {
        super("example title", "example description");

        TinmanShooter.maxBulletLife = maxBulletLife;
        TinmanShooter.speed = speed;

        TinmanShooter.createGun(useMasterskin);
    }


    private static void createGun(boolean userMasterskin){
        ItemStack item = userMasterskin
                ? new ItemStack(Material.IRON_HOE)
                : new ItemStack(Material.GOLDEN_HOE);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.AQUA + "Tinmans's Shooter");

        List<String> lore = new ArrayList<>();
        lore.add("This is example lore");
        meta.setLore(lore);

        item.setItemMeta(meta);

        tinmanShooter = item;
    }

    public static int getSpeed() {
        return speed != 0 ? speed : 1;
    }

    public static int getMaxBulletLife() {
        return maxBulletLife != 0 ? maxBulletLife : 100;
    }

    public static ItemStack getTinmanShooter() {
        return tinmanShooter;
    }

}
