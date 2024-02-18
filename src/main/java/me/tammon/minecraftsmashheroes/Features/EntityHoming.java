package me.tammon.minecraftsmashheroes.Features;

import me.tammon.minecraftsmashheroes.MinecraftSmashHeroes;
import org.bukkit.FluidCollisionMode;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

import java.util.function.Predicate;

public class EntityHoming extends AbstractHoming {
    private EntityType homingEntityDefaultType;
    private Entity homingEntity;

    public EntityHoming(Predicate<Entity> onHitEntity, Predicate<Block> onHitBlock) {
        super(onHitEntity, onHitBlock);
    }

    public EntityHoming(long maxLifeTime, Predicate<Entity> onHitEntity, Predicate<Block> onHitBlock){
        super(maxLifeTime, onHitEntity, onHitBlock);
    }

    public void setHomingEntityDefaultType(EntityType entityDefaultType){
        this.homingEntityDefaultType = entityDefaultType;
    }

    public void setHomingEntity(Entity homingEntity){
        this.homingEntity = homingEntity;
    }

    @Override
    public BukkitTask start(Location spawnLocation, Entity target, float speed) {
        BukkitTask bukkitTask;
        if (homingEntityDefaultType != null){
            final Entity entity = target.getWorld().spawnEntity(spawnLocation, homingEntityDefaultType);
            bukkitTask = this.privateStart(entity, target, speed);
        }

        else if (homingEntity != null) {
            bukkitTask = this.privateStart(homingEntity, target, speed);
        }

        else {
            throw new RuntimeException("Either the default homing entity type or the homing entity itself must be set prior.");
        }

        return bukkitTask;
    }


    private BukkitTask privateStart(final Entity homingEntity, Entity target, float speed) {
        BukkitRunnable runnableHoming = new BukkitRunnable() {
            long ticksRemaining = maxLifeTime;
            private void kill(){
                if(!keepHomingAliveAfterKill){
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

                Location vehiclePosition = homingEntity.getLocation().clone();
                Vector direction = targetPosition.subtract(vehiclePosition).toVector().normalize();

                RayTraceResult rayTraceResult = target.getWorld().rayTrace(
                        homingEntity.getLocation(),
                        direction,
                        1, // max distance
                        FluidCollisionMode.NEVER,
                        true,
                        1,
                        entity -> entity.getEntityId() != homingEntity.getEntityId()
                );

                boolean killHoming = false;

                if (rayTraceResult == null){
                    homingEntity.setVelocity(direction.clone().multiply(speed));
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
