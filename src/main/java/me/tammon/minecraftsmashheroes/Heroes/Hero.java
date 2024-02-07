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
    protected Ability smash;
    protected Ability passive;

    protected int primaryInventorySlot;
    protected int secondaryInventorySlot;
    protected int smashInventorySlot;


    public Hero(Player player, HeroNameEnum name, Prestige prestige){
        this.player = player;
        this.name = name;

        this.prestige = prestige;
        this.maxHealthPoints = prestige.getValue();

    }

    /** Fired right when the game is about to start
     * Should start any timer, set proper hps, etc.
     * True on success, False otherwise */
    public abstract boolean start();

    /** Fired when you want to reset stuff to their
     * default settings or states.
     * True on success, False otherwise */
    public abstract boolean cleanup();

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

    /** Fired when player tries to scroll
     * or select a different hot bar slot */
    public abstract void usedAbilityAt(int inventorySlot);

    /** Fired when a player attacks */
    public abstract void attackedWithAbilityAt(int inventorySlot);
    public abstract void onPrimaryUse();
    public abstract void onPrimaryAttack();
    public abstract void onSecondaryUse();
    public abstract void onSecondaryAttack();
    public abstract void onSmashUse();
    public abstract void onSmashAttack();

    public abstract void onPassiveUse();
}
