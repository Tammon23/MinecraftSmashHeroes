package me.tammon.minecraftsmashheroes.Features;

import me.tammon.minecraftsmashheroes.MinecraftSmashHeroes;
import org.bukkit.Location;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.*;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Transformation;

import java.util.List;

public class Rideable extends BukkitRunnable {
    private final BlockDisplay rideable_item;
    private final ArmorStand vehicle;
    private int max_life; // number of ticks rideable should stay alive for
    private final int speed;
    private boolean is_running = false;

    public Rideable(Player rider, BlockData rideable_item_data, int max_life){
        this(rider, rideable_item_data, max_life, 1);
    }

    public Rideable(Player rider, BlockData rideable_item_data, int max_life, int speed){
        this.max_life = max_life;

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
        blockDisplay.setBlock(rideable_item_data);

        blockDisplay.setTeleportDuration(1);
        this.rideable_item = blockDisplay;

        this.vehicle = (ArmorStand) rider.getWorld().spawnEntity(rider.getLocation(), EntityType.ARMOR_STAND);
        this.vehicle.setVisible(false);
        this.vehicle.setSmall(true);
        this.vehicle.addPassenger(this.rideable_item);

        this.speed = speed;
    }

    public void mount(Player rider){
        this.rideable_item.addPassenger(rider);
    }

    public void dismount(Player rider){
        this.rideable_item.removePassenger(rider);
    }

    public boolean IsRunning() {
        return is_running;
    }

    public synchronized void animate() throws IllegalArgumentException, IllegalStateException {
        super.runTaskTimer(MinecraftSmashHeroes.PLUGIN, 0L, 1L);
        this.is_running = true;
    }

    private void kill () {
        this.vehicle.eject();
        this.vehicle.remove();
        this.rideable_item.eject();
        this.rideable_item.remove();
        this.is_running = false;
        this.cancel();
    }

    @Override
    public void run() {
        if (this.max_life-- < 1){
            this.kill();
            return;
        }

        List<Entity> passengers = this.rideable_item.getPassengers();

        if (!passengers.isEmpty()){
            Entity pilot = passengers.get(0);
            Location location = pilot.getLocation();

            this.rideable_item.setRotation(location.getYaw(), location.getPitch());
            this.vehicle.setVelocity(location.getDirection().multiply(this.speed));
        }
    }
}
