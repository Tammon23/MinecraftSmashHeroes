package me.tammon.minecraftsmashheroes.Heroes.CustomHeroes.Skullfire;

import me.tammon.minecraftsmashheroes.Features.BaseAbilities.InstantRanged;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class SkullfireGun extends InstantRanged {
    private static ItemStack skullfireGun;
    public final static int DAMAGE_DISTANCE = 30;

    public SkullfireGun() {
        this(false);
    }

    public SkullfireGun(boolean useMasterskin) {
        createGun(useMasterskin);
    }

    private static void createGun(boolean userMasterskin){
        ItemStack item = userMasterskin
                ? new ItemStack(Material.DIAMOND_SHOVEL)
                : new ItemStack(Material.DIAMOND_PICKAXE);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.AQUA + "Skullfire's Gun");

        List<String> lore = new ArrayList<>();
        lore.add("This is example lore");
        meta.setLore(lore);

        item.setItemMeta(meta);

        skullfireGun = item;
    }

    public static ItemStack getSkullfireGun() {
        return skullfireGun;
    }
}
