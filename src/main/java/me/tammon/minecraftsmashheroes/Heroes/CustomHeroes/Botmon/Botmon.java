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

        this.base_skin = new Skin(
                this.name,
                ChatColor.AQUA + "%s's %s",
                new ItemStack(Material.BROWN_CARPET),
                Helper.GetDyedLeatherArmour(Material.LEATHER_CHESTPLATE, 	26, 42, 52),
                Helper.GetDyedLeatherArmour(Material.LEATHER_LEGGINGS, 	26, 42, 52),
                Helper.GetDyedLeatherArmour(Material.LEATHER_BOOTS, 	26, 42, 52)
        );

        this.master_skin = new Skin(
                this.name,
                ChatColor.AQUA + "%s's Master %s",
                new ItemStack(Material.DISPENSER),
                Helper.GetDyedLeatherArmour(Material.LEATHER_CHESTPLATE,202, 22, 22),
                Helper.GetDyedLeatherArmour(Material.LEATHER_LEGGINGS, 255, 255, 0),
                Helper.GetDyedLeatherArmour(Material.LEATHER_BOOTS, 	26, 42, 52)
        );
    }
}
