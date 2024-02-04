package me.tammon.minecraftsmashheroes.Heroes;

import org.bukkit.entity.Player;

public abstract class Hero {
    protected Player player;
    protected HeroNameEnum name;
    protected double maxHealthPoints;
    protected Skin baseSkin;
    protected Skin masterSkin;

    protected final Prestige prestige;
    protected Ability primary;
    protected Ability secondary;
    protected Ability smashAbility;
    protected Ability passive;


    public Hero(Player player, HeroNameEnum name, Prestige prestige){
        this.player = player;
        this.name = name;

        this.prestige = prestige;
        this.maxHealthPoints = prestige.getValue();

    }


    /** Used to apply armour the player using this hero */
    public void ApplyOutfit(boolean isMasters){
        if (isMasters && this.masterSkin != null){
            this.player.getInventory().setHelmet(this.masterSkin.helmet);
            this.player.getInventory().setChestplate(this.masterSkin.chestPlate);
            this.player.getInventory().setLeggings(this.masterSkin.leggings);
            this.player.getInventory().setBoots(this.masterSkin.boots);

        } else if (!isMasters && this.baseSkin != null) {
            this.player.getInventory().setHelmet(this.baseSkin.helmet);
            this.player.getInventory().setChestplate(this.baseSkin.chestPlate);
            this.player.getInventory().setLeggings(this.baseSkin.leggings);
            this.player.getInventory().setBoots(this.baseSkin.boots);
        }
    }
}
