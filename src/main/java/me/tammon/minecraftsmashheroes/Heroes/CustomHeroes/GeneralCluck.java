package me.tammon.minecraftsmashheroes.Heroes.CustomHeroes;

import me.tammon.minecraftsmashheroes.Helper;
import me.tammon.minecraftsmashheroes.Heroes.Hero;
import me.tammon.minecraftsmashheroes.Heroes.HeroNameEnum;
import me.tammon.minecraftsmashheroes.Heroes.Prestige;
import me.tammon.minecraftsmashheroes.Heroes.Skin;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GeneralCluck extends Hero {
    public GeneralCluck(Player player, Prestige prestige) {
        super(player, HeroNameEnum.GENERAL_CLUCK, prestige);

        this.base_skin = new Skin(
                this.name,
                ChatColor.AQUA + "%s's %s",
                new ItemStack(Material.MAGENTA_CARPET),
                Helper.GetDyedLeatherArmour(Material.LEATHER_CHESTPLATE, 	249, 138, 63),
                Helper.GetDyedLeatherArmour(Material.LEATHER_LEGGINGS,68, 72, 41),
                Helper.GetDyedLeatherArmour(Material.LEATHER_BOOTS,224, 224, 224)
        );

        this.master_skin = new Skin(
                this.name,
                ChatColor.AQUA + "%s's Master %s",
                new ItemStack(Material.LIGHT_BLUE_CARPET),
                Helper.GetDyedLeatherArmour(Material.LEATHER_CHESTPLATE,224, 224, 224),
                Helper.GetDyedLeatherArmour(Material.LEATHER_LEGGINGS, 68, 72, 41),
                Helper.GetDyedLeatherArmour(Material.LEATHER_BOOTS,224, 224, 224)
        );
    }
}
