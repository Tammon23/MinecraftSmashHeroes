package me.tammon.minecraftsmashheroes.Heroes.CustomHeroes;

import me.tammon.minecraftsmashheroes.Heroes.Hero;
import me.tammon.minecraftsmashheroes.Heroes.HeroNameEnum;
import me.tammon.minecraftsmashheroes.Heroes.Prestige;
import me.tammon.minecraftsmashheroes.Heroes.Skin;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Bulk extends Hero {

    public Bulk(Player player, Prestige prestige) {
        super(player, HeroNameEnum.BULK, prestige,
                new Skin(
                        new ItemStack(Material.DIAMOND_ORE),
                        new ItemStack(Material.LEATHER_CHESTPLATE),
                        new ItemStack(Material.LEATHER_LEGGINGS),
                        new ItemStack(Material.LEATHER_BOOTS)
                ),
                new Skin(
                        new ItemStack(Material.CYAN_WOOL),
                        new ItemStack(Material.LEATHER_CHESTPLATE),
                        new ItemStack(Material.LEATHER_LEGGINGS),
                        (new ItemStack(Material.LEATHER_BOOTS))
                ));
    }
}
