package me.tammon.minecraftsmashheroes.Heroes;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Skin {
    protected ItemStack helmet;
    protected ItemStack chest_plate;
    protected ItemStack leggings;
    protected ItemStack boots;

    public Skin(HeroNameEnum hero, String format_string, ItemStack helmet, ItemStack chest_plate, ItemStack leggings, ItemStack boots){
        this.helmet = helmet;
        this.chest_plate = chest_plate;
        this.leggings = leggings;
        this.boots = boots;

        ItemMeta helmet_meta = this.helmet.getItemMeta();
        helmet_meta.setDisplayName(format_string.formatted(hero.get_clean_name(), "Mask"));
        this.helmet.setItemMeta(helmet_meta);

        ItemMeta chest_piece_meta = this.chest_plate.getItemMeta();
        chest_piece_meta.setDisplayName(format_string.formatted(hero.get_clean_name(), "Chestpiece"));
        this.chest_plate.setItemMeta(chest_piece_meta);

        ItemMeta leggings_meta = this.leggings.getItemMeta();
        leggings_meta.setDisplayName(format_string.formatted(hero.get_clean_name(), "Pants"));
        this.leggings.setItemMeta(leggings_meta);

        ItemMeta boots_meta = this.boots.getItemMeta();
        boots_meta.setDisplayName(format_string.formatted(hero.get_clean_name(), "Boots"));
        this.boots.setItemMeta(boots_meta);


    }
}
