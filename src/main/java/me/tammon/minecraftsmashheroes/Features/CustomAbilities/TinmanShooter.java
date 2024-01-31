package me.tammon.minecraftsmashheroes.Features.CustomAbilities;

import me.tammon.minecraftsmashheroes.Features.BaseAbilities.DelayedRanged;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class TinmanShooter extends DelayedRanged implements Listener {
    private static ItemStack tinman_shooter;
    private static int max_bullet_life;
    private static int speed;

    public TinmanShooter(int max_bullet_life, int speed) {
        this(max_bullet_life, speed, false);
    }


    public TinmanShooter(int max_bullet_life, int speed, boolean use_masterskin) {
        super("example title", "example description");

        TinmanShooter.max_bullet_life = max_bullet_life;
        TinmanShooter.speed = speed;

        TinmanShooter.createGun(use_masterskin);
    }


    private static void createGun(boolean is_masterskinned){
        ItemStack item = is_masterskinned
                ? new ItemStack(Material.IRON_HOE)
                : new ItemStack(Material.GOLDEN_HOE);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.AQUA + "Tinmans's Shooter");

        List<String> lore = new ArrayList<>();
        lore.add("This is example lore");
        meta.setLore(lore);

        item.setItemMeta(meta);

        tinman_shooter = item;
    }

    public static int getSpeed() {
        return speed != 0 ? speed : 1;
    }

    public static int getMax_bullet_life() {
        return max_bullet_life != 0 ? max_bullet_life : 100;
    }

    public static ItemStack getTinman_shooter() {
        return tinman_shooter;
    }


}
