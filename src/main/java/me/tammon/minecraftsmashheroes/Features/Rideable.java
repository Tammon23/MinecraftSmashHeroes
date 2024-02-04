package me.tammon.minecraftsmashheroes.Features;

import me.tammon.minecraftsmashheroes.MinecraftSmashHeroes;
import org.bukkit.Location;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.*;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Transformation;

import java.util.List;

public class Rideable extends BukkitRunnable {
    private final BlockDisplay rideableItem;
    private final ArmorStand vehicle;
    private int maxLife; // number of ticks rideable should stay alive for
    private final int speed;
    private boolean isRunning = false;

    public Rideable(Player rider, BlockData rideableItemData, int maxLife){
        this(rider, rideableItemData, maxLife, 1);
    }

    public Rideable(Player rider, BlockData rideableItemData, int maxLife, int speed){
        this.maxLife = maxLife;

        BlockDisplay blockDisplay = (BlockDisplay) rider.getWorld().spawnEntity(
                rider.getLocation(),
                EntityType.BLOCK_DISPLAY
        );

        Location direction = rider.getLocation();

        Transformation transformation = blockDisplay.getTransformation();
        transformation.getTranslation().x = -1f;
        transformation.getTranslation().y = -1f;
        transformation.getTranslation().z =  1f;
        transformation.getLeftRotation().y = 1f;


        blockDisplay.setRotation(direction.getYaw(), direction.getPitch());
        blockDisplay.setTransformation(transformation);
        blockDisplay.setBlock(rideableItemData);

        blockDisplay.setTeleportDuration(1);
        this.rideableItem = blockDisplay;

        this.vehicle = (ArmorStand) rider.getWorld().spawnEntity(rider.getLocation(), EntityType.ARMOR_STAND);
        this.vehicle.setVisible(false);
        this.vehicle.setSmall(true);
        this.vehicle.addPassenger(this.rideableItem);

        this.speed = speed;
    }

    public void mount(Player rider){
        this.rideableItem.addPassenger(rider);
    }

    public void dismount(Player rider){
        this.rideableItem.removePassenger(rider);
    }

    public boolean isRunning() {
        return isRunning;
    }

    public synchronized void animate() {
        super.runTaskTimer(MinecraftSmashHeroes.PLUGIN, 0L, 1L);
        this.isRunning = true;
    }

    private void kill () {
        this.vehicle.eject();
        this.vehicle.remove();
        this.rideableItem.eject();
        this.rideableItem.remove();
        this.isRunning = false;
        this.cancel();
    }

    @Override
    public void run() {
        if (this.maxLife-- < 1){
            this.kill();
            return;
        }

        List<Entity> passengers = this.rideableItem.getPassengers();

        if (!passengers.isEmpty()){
            Entity pilot = passengers.get(0);
            Location location = pilot.getLocation();

            this.rideableItem.setRotation(location.getYaw(), location.getPitch());
            this.vehicle.setVelocity(location.getDirection().multiply(this.speed));
        }
    }
}
