package me.tammon.minecraftsmashheroes.Heroes.CustomHeroes.GeneralCluck;

import me.tammon.minecraftsmashheroes.Features.BaseAbilities.DelayedRanged;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Blaster implements DelayedRanged {
    private static ItemStack blaster;
    private static int maxBulletLife;
    private static int speed;

    public Blaster(int maxBulletLife, int speed) {
        this(maxBulletLife, speed, false);

    }

    public Blaster(int maxBulletLife, int speed, boolean useMasterskin) {
        Blaster.maxBulletLife = maxBulletLife;
        Blaster.speed = speed;

        Blaster.createGun(useMasterskin);
    }

    private static void createGun(boolean useMasterskin){
        ItemStack item = new ItemStack(Material.WOODEN_AXE);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.AQUA + "General Cluck's Blaster");

        List<String> lore = new ArrayList<>();
        lore.add("This is example lore");
        meta.setLore(lore);

        item.setItemMeta(meta);

        blaster = item;
    }

    public static int getSpeed() {
        return speed != 0 ? speed : 1;
    }

    public static int getMaxBulletLife() {
        return maxBulletLife != 0 ? maxBulletLife : 100;
    }

    public static ItemStack get_general_cluck_blaster() {
        return blaster;
    }
}
