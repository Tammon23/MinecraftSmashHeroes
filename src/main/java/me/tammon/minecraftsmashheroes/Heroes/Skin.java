package me.tammon.minecraftsmashheroes.Heroes;

import org.bukkit.inventory.ItemStack;

public class Skin {
    protected ItemStack helmet;
    protected ItemStack chest_plate;
    protected ItemStack leggings;
    protected ItemStack boots;

    public Skin(ItemStack helmet, ItemStack chest_plate, ItemStack leggings, ItemStack boots){
        this.helmet = helmet;
        this.chest_plate = chest_plate;
        this.leggings = leggings;
        this.boots = boots;
    }
}
