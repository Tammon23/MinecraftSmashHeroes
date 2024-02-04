package me.tammon.minecraftsmashheroes.Heroes;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Skin {
    protected ItemStack helmet;
    protected ItemStack chestPlate;
    protected ItemStack leggings;
    protected ItemStack boots;

    public Skin(HeroNameEnum hero, String formatString, ItemStack helmet, ItemStack chestPlate, ItemStack leggings, ItemStack boots){
        this.helmet = helmet;
        this.chestPlate = chestPlate;
        this.leggings = leggings;
        this.boots = boots;

        ItemMeta helmetItemMeta = this.helmet.getItemMeta();
        helmetItemMeta.setDisplayName(String.format(formatString, hero.getCleanName(), "Mask"));
        this.helmet.setItemMeta(helmetItemMeta);

        ItemMeta chestPlateItemMeta = this.chestPlate.getItemMeta();
        chestPlateItemMeta.setDisplayName(String.format(formatString, hero.getCleanName(), "Chestpiece"));
        this.chestPlate.setItemMeta(chestPlateItemMeta);

        ItemMeta leggingsItemMeta = this.leggings.getItemMeta();
        leggingsItemMeta.setDisplayName(String.format(formatString, hero.getCleanName(), "Pants"));
        this.leggings.setItemMeta(leggingsItemMeta);

        ItemMeta bootsItemMeta = this.boots.getItemMeta();
        bootsItemMeta.setDisplayName(String.format(formatString, hero.getCleanName(), "Boots"));
        this.boots.setItemMeta(bootsItemMeta);
    }
}
