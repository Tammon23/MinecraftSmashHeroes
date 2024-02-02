package me.tammon.minecraftsmashheroes.Heroes.CustomHeroes.Bulk;

import me.tammon.minecraftsmashheroes.Helper;
import me.tammon.minecraftsmashheroes.Heroes.Hero;
import me.tammon.minecraftsmashheroes.Heroes.HeroNameEnum;
import me.tammon.minecraftsmashheroes.Heroes.Prestige;
import me.tammon.minecraftsmashheroes.Heroes.Skin;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Bulk extends Hero {

    public Bulk(Player player, Prestige prestige) {
        super(player, HeroNameEnum.BULK, prestige);

        this.base_skin = new Skin(
                this.name,
                ChatColor.AQUA + "%s's %s",
                new ItemStack(Material.DIAMOND_ORE),
                Helper.GetDyedLeatherArmour(Material.LEATHER_CHESTPLATE,53, 119, 73),
                Helper.GetDyedLeatherArmour(Material.LEATHER_LEGGINGS,49,22,81),
                Helper.GetDyedLeatherArmour(Material.LEATHER_BOOTS,53, 119, 73)
        );

        this.master_skin = new Skin(
                this.name,
                ChatColor.AQUA + "%s's Master %s",
                new ItemStack(Material.CYAN_WOOL),
                Helper.GetDyedLeatherArmour(Material.LEATHER_CHESTPLATE,138, 165, 147),
                Helper.GetDyedLeatherArmour(Material.LEATHER_LEGGINGS, 49,22,81),
                Helper.GetDyedLeatherArmour(Material.LEATHER_BOOTS,138, 165, 147)
        );
    }
}
