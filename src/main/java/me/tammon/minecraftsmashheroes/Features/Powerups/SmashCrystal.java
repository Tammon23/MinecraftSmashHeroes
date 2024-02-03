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
    private final Location spawn_location;
    private final BlockData ready_state;
    private final BlockDisplay smash_crystal;
    private final long countdown_timer;
    private final int id;

    private Predicate<Collection<Entity>> on_collided = (entity -> true);


    public SmashCrystal(World world, Location spawn_location, BlockData loading_state, BlockData ready_state, long countdown_timer){
        this.world = world;
        this.spawn_location = spawn_location;
        this.ready_state = ready_state;
        this.countdown_timer = countdown_timer;

        BlockDisplay blockDisplay = (BlockDisplay) world.spawnEntity(
                spawn_location,
                EntityType.BLOCK_DISPLAY
        );

        Transformation transformation = blockDisplay.getTransformation();
        transformation.getTranslation().x = -0.5f;

        blockDisplay.setTransformation(transformation);
        blockDisplay.setBlock(loading_state);

        this.smash_crystal = blockDisplay;
        this.id = blockDisplay.getEntityId();
    }

    public void setTransformation(Transformation transformation){
        this.smash_crystal.setTransformation(transformation);
    }

    public void setOn_collided(Predicate<Collection<Entity>> on_collided){
        this.on_collided = on_collided;
    }

    public void start(){

        new BukkitRunnable() {
            int i = 0;

            @Override
            public void run(){
                if (i > 360){
                    i = 0;
                }
                smash_crystal.setRotation(i, 0);
                i += 4;
            }
        }.runTaskTimer(MinecraftSmashHeroes.PLUGIN, 0, 1);

        // change block data of current block display to ready state
        // start runnable to detect when it gets collided with
        // limit check to every 5 ticks
        Bukkit.getScheduler().runTaskLater(MinecraftSmashHeroes.PLUGIN, () -> {
            this.smash_crystal.setBlock(this.ready_state);
            super.runTaskTimer(MinecraftSmashHeroes.PLUGIN, 0, 5);
        }, this.countdown_timer);

    }


    @Override
    public void run() {
        // if we collide with an entity, relay it
        Collection<Entity> nearbyEntities = world.getNearbyEntities(
                this.spawn_location,
                1, 1, 1,
                entity -> entity.getEntityId() != this.id);

        if (!nearbyEntities.isEmpty())
            if (this.on_collided.test(nearbyEntities)){
                this.smash_crystal.remove();
                this.cancel();
            }

    }
}
