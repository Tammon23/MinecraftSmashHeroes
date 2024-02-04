package me.tammon.minecraftsmashheroes.Heroes.CustomHeroes.Karakot;

import me.tammon.minecraftsmashheroes.Features.BaseAbilities.DelayedRanged;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Kamehameha extends DelayedRanged {

    private static ItemStack kamehamehaHand;
    private static int maxBulletLife;
    private static int speed;

    public Kamehameha(int maxBulletLife, int speed) {
        this(maxBulletLife, speed, false);
    }


    public Kamehameha(int maxBulletLife, int speed, boolean useMasterskin) {
        super("example title", "example description");

        Kamehameha.maxBulletLife = maxBulletLife;
        Kamehameha.speed = speed;

        Kamehameha.createGun(useMasterskin);
    }


    private static void createGun(boolean useMasterskin){
        ItemStack item = new ItemStack(Material.STONE_AXE);


        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.AQUA + "Karakot's Fist");

        List<String> lore = new ArrayList<>();
        lore.add("This is example lore");
        meta.setLore(lore);

        item.setItemMeta(meta);

        kamehamehaHand = item;
    }

    public static int getSpeed() {
        return speed != 0 ? speed : 1;
    }

    public static int getMaxBulletLife() {
        return maxBulletLife != 0 ? maxBulletLife : 100;
    }

    public static ItemStack getKamehamehaHand() {
        return kamehamehaHand;
    }
}
