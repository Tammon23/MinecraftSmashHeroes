package me.tammon.minecraftsmashheroes.Heroes.CustomHeroes.CakeMonster;

import me.tammon.minecraftsmashheroes.Util.Helper;
import me.tammon.minecraftsmashheroes.Heroes.Hero;
import me.tammon.minecraftsmashheroes.Heroes.HeroNameEnum;
import me.tammon.minecraftsmashheroes.Heroes.Prestige;
import me.tammon.minecraftsmashheroes.Heroes.Skin;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CakeMonster extends Hero {
    public CakeMonster(Player player, Prestige prestige) {
        super(player, HeroNameEnum.CAKE_MONSTER, prestige);

        this.baseSkin = new Skin(
                this.name,
                ChatColor.AQUA + "%s's %s",
                new ItemStack(Material.BLUE_CARPET),
                Helper.getDyedLeatherArmour(Material.LEATHER_CHESTPLATE,172, 84, 0),
                Helper.getDyedLeatherArmour(Material.LEATHER_LEGGINGS,172, 84, 0),
                Helper.getDyedLeatherArmour(Material.LEATHER_BOOTS,172, 84, 0)
        );

        this.masterSkin = new Skin(
                this.name,
                ChatColor.AQUA + "%s's Master %s",
                new ItemStack(Material.YELLOW_CARPET),
                Helper.getDyedLeatherArmour(Material.LEATHER_CHESTPLATE,255, 255, 255),
                Helper.getDyedLeatherArmour(Material.LEATHER_LEGGINGS, 172, 84, 0),
                Helper.getDyedLeatherArmour(Material.LEATHER_BOOTS,255, 255, 255)
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
