package me.tammon.minecraftsmashheroes.Heroes.CustomHeroes.GeneralCluck;

import me.tammon.minecraftsmashheroes.Features.BaseAbilities.DelayedRanged;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Blaster extends DelayedRanged {
    private static ItemStack blaster;
    private static int max_bullet_life;
    private static int speed;

    public Blaster(int max_bullet_life,  int speed) {
        this(max_bullet_life, speed, false);

    }

    public Blaster(int max_bullet_life, int speed, boolean use_masterskin) {
        super("example title", "example description");

        Blaster.max_bullet_life = max_bullet_life;
        Blaster.speed = speed;

        Blaster.createGun(use_masterskin);
    }

    private static void createGun(boolean is_masterskinned){
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

    public static int getMax_bullet_life() {
        return max_bullet_life != 0 ? max_bullet_life : 100;
    }

    public static ItemStack get_general_cluck_blaster() {
        return blaster;
    }
}
