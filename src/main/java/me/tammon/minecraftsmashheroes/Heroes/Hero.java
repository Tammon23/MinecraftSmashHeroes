package me.tammon.minecraftsmashheroes.Heroes;

import org.bukkit.entity.Player;

public abstract class Hero {
    protected Player player;
    protected HeroNameEnum name;
    protected double max_health_points;
    protected Skin base_skin;
    protected Skin master_skin;

    protected final Prestige prestige;
    protected Ability primary;
    protected Ability secondary;
    protected Ability smash_ability;
    protected Ability passive;


    public Hero(Player player, HeroNameEnum name, Prestige prestige){
        this.player = player;
        this.name = name;

        this.prestige = prestige;
        this.max_health_points = prestige.getValue();

    }


    /** Used to apply armour the player using this hero */
    public void ApplyOutfit(boolean is_masters){
        if (is_masters && this.master_skin != null){
            this.player.getInventory().setHelmet(this.master_skin.helmet);
            this.player.getInventory().setChestplate(this.master_skin.chest_plate);
            this.player.getInventory().setLeggings(this.master_skin.leggings);
            this.player.getInventory().setBoots(this.master_skin.boots);

        } else if (!is_masters && this.base_skin != null) {
            this.player.getInventory().setHelmet(this.base_skin.helmet);
            this.player.getInventory().setChestplate(this.base_skin.chest_plate);
            this.player.getInventory().setLeggings(this.base_skin.leggings);
            this.player.getInventory().setBoots(this.base_skin.boots);
        }
    }
}
