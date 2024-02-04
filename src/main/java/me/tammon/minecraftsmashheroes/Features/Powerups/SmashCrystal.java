package me.tammon.minecraftsmashheroes.Features.Powerups;

import me.tammon.minecraftsmashheroes.MinecraftSmashHeroes;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Transformation;

import java.util.Collection;
import java.util.function.Predicate;

public class SmashCrystal extends BukkitRunnable {
    private final World world;
    private final Location spawnLocation;
    private final BlockData readyState;
    private final BlockDisplay smashCrystal;
    private final long countdownTimer;
    private final int id;

    private Predicate<Collection<Entity>> onCollided = (entity -> true);


    public SmashCrystal(World world, Location spawnLocation, BlockData loadingState, BlockData readyState, long countdownTimer){
        this.world = world;
        this.spawnLocation = spawnLocation;
        this.readyState = readyState;
        this.countdownTimer = countdownTimer;

        BlockDisplay blockDisplay = (BlockDisplay) world.spawnEntity(
                spawnLocation,
                EntityType.BLOCK_DISPLAY
        );

        Transformation transformation = blockDisplay.getTransformation();
        transformation.getTranslation().x = -0.5f;

        blockDisplay.setTransformation(transformation);
        blockDisplay.setBlock(loadingState);

        this.smashCrystal = blockDisplay;
        this.id = blockDisplay.getEntityId();
    }

    public void setTransformation(Transformation transformation){
        this.smashCrystal.setTransformation(transformation);
    }

    public void setOnCollided(Predicate<Collection<Entity>> onCollided){
        this.onCollided = onCollided;
    }

    public void start(){

        new BukkitRunnable() {
            int i = 0;

            @Override
            public void run(){
                if (i > 360){
                    i = 0;
                }
                smashCrystal.setRotation(i, 0);
                i += 4;
            }
        }.runTaskTimer(MinecraftSmashHeroes.PLUGIN, 0, 1);

        // change block data of current block display to ready state
        // start runnable to detect when it gets collided with
        // limit check to every 5 ticks
        Bukkit.getScheduler().runTaskLater(MinecraftSmashHeroes.PLUGIN, () -> {
            this.smashCrystal.setBlock(this.readyState);
            super.runTaskTimer(MinecraftSmashHeroes.PLUGIN, 0, 5);
        }, this.countdownTimer);

    }


    @Override
    public void run() {
        // if we collide with an entity, relay it
        Collection<Entity> nearbyEntities = world.getNearbyEntities(
                this.spawnLocation,
                1, 1, 1,
                entity -> entity.getEntityId() != this.id);

        if (!nearbyEntities.isEmpty())
            if (this.onCollided.test(nearbyEntities)){
                this.smashCrystal.remove();
                this.cancel();
            }
    }
}
