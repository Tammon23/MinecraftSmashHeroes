package me.tammon.minecraftsmashheroes.Features.CustomAbilities;

import me.tammon.minecraftsmashheroes.Features.BaseAbilities.InstantRanged;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class SkullfireGun extends InstantRanged {
    private static ItemStack skullfire_gun;
    public final static int damage_distance = 30;

    public SkullfireGun() {
        super("example title", "example description");
        createGun(false);
    }

    public SkullfireGun(boolean user_masterskin) {
        super("example title", "example description");
        createGun(user_masterskin);
    }

    private static void createGun(boolean is_masterskinned){
        ItemStack item = is_masterskinned
                ? new ItemStack(Material.DIAMOND_SHOVEL)
                : new ItemStack(Material.DIAMOND_PICKAXE);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.AQUA + "Skullfire's Gun");

        List<String> lore = new ArrayList<>();
        lore.add("This is example lore");
        meta.setLore(lore);

        item.setItemMeta(meta);

        skullfire_gun = item;
    }

    public static ItemStack getSkullfire_gun() {
        return skullfire_gun;
    }
}
