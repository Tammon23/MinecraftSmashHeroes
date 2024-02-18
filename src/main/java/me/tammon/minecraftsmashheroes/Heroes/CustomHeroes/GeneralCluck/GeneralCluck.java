package me.tammon.minecraftsmashheroes.Heroes.CustomHeroes.GeneralCluck;

import me.tammon.minecraftsmashheroes.Util.Helper;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GeneralCluck extends Hero {
    public GeneralCluck(Player player, Prestige prestige) {
        super(player, HeroNameEnum.GENERAL_CLUCK, prestige);

        this.baseSkin = new Skin(
                this.name,
                ChatColor.AQUA + "%s's %s",
                new ItemStack(Material.MAGENTA_CARPET),
                Helper.getDyedLeatherArmour(Material.LEATHER_CHESTPLATE, 	249, 138, 63),
                Helper.getDyedLeatherArmour(Material.LEATHER_LEGGINGS,68, 72, 41),
                Helper.getDyedLeatherArmour(Material.LEATHER_BOOTS,224, 224, 224)
        );

        this.masterSkin = new Skin(
                this.name,
                ChatColor.AQUA + "%s's Master %s",
                new ItemStack(Material.LIGHT_BLUE_CARPET),
                Helper.getDyedLeatherArmour(Material.LEATHER_CHESTPLATE,224, 224, 224),
                Helper.getDyedLeatherArmour(Material.LEATHER_LEGGINGS, 68, 72, 41),
                Helper.getDyedLeatherArmour(Material.LEATHER_BOOTS,224, 224, 224)
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
