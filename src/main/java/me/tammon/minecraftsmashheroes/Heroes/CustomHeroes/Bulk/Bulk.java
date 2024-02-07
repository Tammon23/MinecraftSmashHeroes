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

        this.baseSkin = new Skin(
                this.name,
                ChatColor.AQUA + "%s's %s",
                new ItemStack(Material.DIAMOND_ORE),
                Helper.getDyedLeatherArmour(Material.LEATHER_CHESTPLATE,53, 119, 73),
                Helper.getDyedLeatherArmour(Material.LEATHER_LEGGINGS,49,22,81),
                Helper.getDyedLeatherArmour(Material.LEATHER_BOOTS,53, 119, 73)
        );

        this.masterSkin = new Skin(
                this.name,
                ChatColor.AQUA + "%s's Master %s",
                new ItemStack(Material.CYAN_WOOL),
                Helper.getDyedLeatherArmour(Material.LEATHER_CHESTPLATE,138, 165, 147),
                Helper.getDyedLeatherArmour(Material.LEATHER_LEGGINGS, 49,22,81),
                Helper.getDyedLeatherArmour(Material.LEATHER_BOOTS,138, 165, 147)
        );
    }

    @Override
    public boolean start() {
        return true;
    }

    @Override
    public boolean cleanup() {
        return true;
    }

    @Override
    public void usedAbilityAt(int inventorySlot) {

    }

    @Override
    public void attackedWithAbilityAt(int inventorySlot) {

    }

    @Override
    public void onPrimaryUse() {

    }

    @Override
    public void onPrimaryAttack() {

    }

    @Override
    public void onSecondaryUse() {

    }

    @Override
    public void onSecondaryAttack() {

    }

    @Override
    public void onSmashUse() {

    }

    @Override
    public void onSmashAttack() {

    }

    @Override
    public void onPassiveUse() {

    }
}
