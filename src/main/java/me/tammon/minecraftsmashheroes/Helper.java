package me.tammon.minecraftsmashheroes;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class Helper {
    public static ItemStack GetDyedLeatherArmour(Material armour_piece, int red, int green, int blue){
        ItemStack item = new ItemStack(armour_piece);
        LeatherArmorMeta item_meta = (LeatherArmorMeta)item.getItemMeta();
        item_meta.setColor(Color.fromRGB(red, green, blue));
        item.setItemMeta(item_meta);

        return item;
    }
}
