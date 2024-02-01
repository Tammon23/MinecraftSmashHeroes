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
    private final ArmorStand armor_stand;
    private int max_life;
    private boolean is_running = false;
    private final int speed;
    private final int bullet_shooter;

    private final BiPredicate<Entity, Vector> onHitEntity;
    private final BiPredicate<Block, Vector> onHitBlock;

    public Bullet(Player shooter, BlockData bullet_material, int max_life, int speed, BiPredicate<Entity, Vector> onHitEntity, BiPredicate<Block, Vector> onHitBlock){
        World world = shooter.getWorld();
        Location spawn_location = shooter.getLocation();

        BlockDisplay blockDisplay = (BlockDisplay) world.spawnEntity(
                spawn_location,
                EntityType.BLOCK_DISPLAY
        );

        Transformation transformation = blockDisplay.getTransformation();
        transformation.getTranslation().x = 1f;
        transformation.getTranslation().y = -1f;
        transformation.getLeftRotation().y = -1f;

        blockDisplay.setBlock(bullet_material);
        blockDisplay.setTransformation(transformation);
        blockDisplay.setRotation(spawn_location.getYaw(), spawn_location.getPitch());

        this.bullet_shooter = shooter.getEntityId();

        this.armor_stand = (ArmorStand) Helper.SpawnInvisibleArmorStand(spawn_location, true, true);
        this.armor_stand.setSmall(true);
        this.armor_stand.addPassenger(blockDisplay);

        this.bullet = blockDisplay;
        this.max_life = max_life;
        this.speed = speed;
        this.onHitEntity = onHitEntity;
        this.onHitBlock = onHitBlock;
    }

    public boolean IsRunning(){
        return this.is_running;
    }

    public void kill(){
        this.max_life = 0;
        this.bullet.remove();
        this.armor_stand.remove();
        this.is_running = false;
        this.cancel();
    }


    public synchronized void animate() throws IllegalArgumentException, IllegalStateException {
        super.runTaskTimer(MinecraftSmashHeroes.PLUGIN, 0L, 1L);
        this.is_running = true;
    }


    @Override
    public void run() {
        if (this.max_life-- < 1){
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
                    return id != bullet_shooter && id != this.armor_stand.getEntityId() && id != bullet.getEntityId();
                }
        );

        if (result != null){
            Vector hit_position = result.getHitPosition();

            Entity entity = result.getHitEntity();
            if (entity != null)
                if (this.onHitEntity.test(entity, hit_position)){
                    this.kill();
                    return;
                }

            Block block = result.getHitBlock();
            if (block != null)
                if (this.onHitBlock.test(block, hit_position)){
                    this.kill();
                    return;
                }
        }

        this.armor_stand.setVelocity(
                location.getDirection().multiply(this.speed)
        );
    }
}
