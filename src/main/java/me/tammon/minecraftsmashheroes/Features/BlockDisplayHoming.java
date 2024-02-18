package me.tammon.minecraftsmashheroes.Features;

import me.tammon.minecraftsmashheroes.MinecraftSmashHeroes;
import me.tammon.minecraftsmashheroes.Util.Helper;
import org.bukkit.FluidCollisionMode;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.*;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Transformation;
import org.bukkit.util.Vector;

import java.util.function.Predicate;

public class BlockDisplayHoming extends AbstractHoming {
    private final BlockData homingEntityBlockData;
    private Transformation homingEntityTransformation;

    public BlockDisplayHoming(BlockData homingEntityBlockData, Predicate<Entity> onHitEntity, Predicate<Block> onHitBlock) {
        super(onHitEntity, onHitBlock);
        this.homingEntityBlockData = homingEntityBlockData;
    }

    public BlockDisplayHoming(BlockData homingEntityBlockData, long maxLifeTime, Predicate<Entity> onHitEntity, Predicate<Block> onHitBlock){
        super(maxLifeTime, onHitEntity, onHitBlock);
        this.homingEntityBlockData = homingEntityBlockData;
    }

    public void setTransformation(Transformation transformation){
        this.homingEntityTransformation = transformation;
    }

    @Override
    public BukkitTask start(Location spawnLocation, Entity target, float speed) {
        final BlockDisplay homingEntity = (BlockDisplay) target.getWorld().spawnEntity(spawnLocation, EntityType.BLOCK_DISPLAY);
        homingEntity.setBlock(this.homingEntityBlockData);
        if (homingEntityTransformation != null)
            homingEntity.setTransformation(homingEntityTransformation);

        final ArmorStand vehicle = (ArmorStand) Helper.spawnInvisibleArmorStand(spawnLocation, true, true);
        vehicle.addPassenger(homingEntity);


        BukkitRunnable runnableHoming = new BukkitRunnable() {
            long ticksRemaining = maxLifeTime;
            private void kill(){
                if(!keepHomingAliveAfterKill){
                    vehicle.remove();
                    homingEntity.remove();
                }
                this.cancel();
            }
            @Override
            public void run() {
                if (ticksRemaining == 0){
                    this.kill();
                    return;
                }
                Location targetPosition;
                if (target instanceof Player)
                    targetPosition = ((Player)(target)).getEyeLocation().clone();
                else
                    targetPosition = target.getLocation().clone();

                Location vehiclePosition = vehicle.getLocation().clone();
                Vector direction = targetPosition.subtract(vehiclePosition).toVector().normalize();

                RayTraceResult rayTraceResult = target.getWorld().rayTrace(
                        homingEntity.getLocation(),
                        direction,
                        1, // max distance
                        FluidCollisionMode.NEVER,
                        true,
                        1,
                        entity -> {
                            final int id = entity.getEntityId();
                            return id != homingEntity.getEntityId() && id != vehicle.getEntityId();
                        }
                );

                boolean killHoming = false;

                if (rayTraceResult == null){
                    vehicle.setVelocity(direction.clone().multiply(speed));
                    ticksRemaining--;
                    return;
                }

                Entity hitEntity = rayTraceResult.getHitEntity();
                if (hitEntity != null)
                    if (onHitEntity != null)
                        killHoming = onHitEntity.test(hitEntity);

                Block hitBlock = rayTraceResult.getHitBlock();
                if (hitBlock != null)
                    if (onHitBlock != null)
                        killHoming = killHoming || onHitBlock.test(hitBlock);

                if (killHoming){
                    kill();
                }
            }
        };
        return runnableHoming.runTaskTimer(MinecraftSmashHeroes.PLUGIN, 1L, 1L);
    }
}
