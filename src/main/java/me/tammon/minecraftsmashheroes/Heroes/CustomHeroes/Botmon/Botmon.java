package me.tammon.minecraftsmashheroes.Heroes.CustomHeroes.Botmon;

import me.tammon.minecraftsmashheroes.Helper;
import me.tammon.minecraftsmashheroes.Heroes.Hero;
import me.tammon.minecraftsmashheroes.Heroes.HeroNameEnum;
import me.tammon.minecraftsmashheroes.Heroes.Prestige;
import me.tammon.minecraftsmashheroes.Heroes.Skin;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Botmon extends Hero {
    public Botmon(Player player, Prestige prestige) {
        super(player, HeroNameEnum.CAKE_MONSTER, prestige);

        this.baseSkin = new Skin(
                this.name,
                ChatColor.AQUA + "%s's %s",
                new ItemStack(Material.BROWN_CARPET),
                Helper.getDyedLeatherArmour(Material.LEATHER_CHESTPLATE, 	26, 42, 52),
                Helper.getDyedLeatherArmour(Material.LEATHER_LEGGINGS, 	26, 42, 52),
                Helper.getDyedLeatherArmour(Material.LEATHER_BOOTS, 	26, 42, 52)
        );

        this.masterSkin = new Skin(
                this.name,
                ChatColor.AQUA + "%s's Master %s",
                new ItemStack(Material.DISPENSER),
                Helper.getDyedLeatherArmour(Material.LEATHER_CHESTPLATE,202, 22, 22),
                Helper.getDyedLeatherArmour(Material.LEATHER_LEGGINGS, 255, 255, 0),
                Helper.getDyedLeatherArmour(Material.LEATHER_BOOTS, 	26, 42, 52)
        );
    }
}
