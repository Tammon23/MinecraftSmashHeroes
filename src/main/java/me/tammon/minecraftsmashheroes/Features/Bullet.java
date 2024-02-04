package me.tammon.minecraftsmashheroes.Features;

import me.tammon.minecraftsmashheroes.Helper;
import me.tammon.minecraftsmashheroes.MinecraftSmashHeroes;
import org.bukkit.FluidCollisionMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.*;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Transformation;
import org.bukkit.util.Vector;

import java.util.function.BiPredicate;


public class Bullet extends BukkitRunnable {

    private final BlockDisplay bullet;
    private final ArmorStand armorStand;
    private int maxLife;
    private boolean isRunning = false;
    private final int speed;
    private final int bulletShooter;

    private final BiPredicate<Entity, Vector> onHitEntity;
    private final BiPredicate<Block, Vector> onHitBlock;

    public Bullet(Player shooter, BlockData bulletMaterial, int maxLife, int speed, BiPredicate<Entity, Vector> onHitEntity, BiPredicate<Block, Vector> onHitBlock){
        World world = shooter.getWorld();
        Location spawnLocation = shooter.getLocation();

        BlockDisplay blockDisplay = (BlockDisplay) world.spawnEntity(
                spawnLocation,
                EntityType.BLOCK_DISPLAY
        );

        blockDisplay.setBlock(bulletMaterial);
        blockDisplay.setRotation(spawnLocation.getYaw(), spawnLocation.getPitch());

        this.bulletShooter = shooter.getEntityId();

        this.armorStand = (ArmorStand) Helper.spawnInvisibleArmorStand(spawnLocation, true, true);
        this.armorStand.setSmall(true);
        this.armorStand.addPassenger(blockDisplay);

        this.bullet = blockDisplay;
        this.maxLife = maxLife;
        this.speed = speed;
        this.onHitEntity = onHitEntity;
        this.onHitBlock = onHitBlock;
    }

    public void setTransformation(Transformation transformation){
        this.bullet.setTransformation(transformation);
    }

    public Transformation getTransformation(){
        return this.bullet.getTransformation();
    }

    public boolean isRunning(){
        return this.isRunning;
    }

    public void kill(){
        this.maxLife = 0;
        this.bullet.remove();
        this.armorStand.remove();
        this.isRunning = false;
        this.cancel();
    }


    public synchronized void animate() {
        super.runTaskTimer(MinecraftSmashHeroes.PLUGIN, 0L, 1L);
        this.isRunning = true;
    }


    @Override
    public void run() {
        if (this.maxLife-- < 1){
            this.kill();
            return;
        }

        Location location = this.bullet.getLocation();

        // ray trace this.speed units forward for entity or block
        RayTraceResult result = this.bullet.getWorld().rayTrace(
                location,
                location.getDirection(),
                this.speed,
                FluidCollisionMode.NEVER,
                true,
                1,
                entity -> {
                    int id = entity.getEntityId();
                    return id != bulletShooter && id != this.armorStand.getEntityId() && id != bullet.getEntityId();
                }
        );

        if (result != null){
            Vector hitPosition = result.getHitPosition();

            Entity entity = result.getHitEntity();
            if (entity != null)
                if (this.onHitEntity.test(entity, hitPosition)){
                    this.kill();
                    return;
                }

            Block block = result.getHitBlock();
            if (block != null)
                if (this.onHitBlock.test(block, hitPosition)){
                    this.kill();
                    return;
                }
        }

        this.armorStand.setVelocity(
                location.getDirection().multiply(this.speed)
        );
    }
}
