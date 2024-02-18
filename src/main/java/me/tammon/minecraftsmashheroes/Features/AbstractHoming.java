package me.tammon.minecraftsmashheroes.Features;

import me.tammon.minecraftsmashheroes.Features.BaseAbilities.DelayedRanged;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitTask;
import java.util.function.Predicate;

public abstract class AbstractHoming  implements DelayedRanged {
    protected Entity target;
    protected final long maxLifeTime;
    protected float speed;
    protected final Predicate<Entity> onHitEntity;
    protected final Predicate<Block> onHitBlock;
    protected boolean keepHomingAliveAfterKill = false;

    // unlimited max lifetime
    public AbstractHoming(Predicate<Entity> onHitEntity, Predicate<Block> onHitBlock){
        this(-1, onHitEntity, onHitBlock);
    }

    public AbstractHoming(long maxLifeTime, Predicate<Entity> onHitEntity, Predicate<Block> onHitBlock){
        this.maxLifeTime = maxLifeTime;
        this.onHitEntity = onHitEntity;
        this.onHitBlock = onHitBlock;
    }

    /** spawns a new Homing entity, and start the movement */
    public abstract BukkitTask start(Location spawnLocation, Entity target, float speed);
}
