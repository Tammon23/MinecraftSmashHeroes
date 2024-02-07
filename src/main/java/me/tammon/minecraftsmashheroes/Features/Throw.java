package me.tammon.minecraftsmashheroes.Features;

import me.tammon.minecraftsmashheroes.Features.BaseAbilities.DelayedRanged;
import me.tammon.minecraftsmashheroes.Helper;
import me.tammon.minecraftsmashheroes.MinecraftSmashHeroes;
import org.bukkit.Bukkit;
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

import java.util.function.Predicate;

public class Throw extends DelayedRanged {
    private final BlockData itemToThrow;
    private Transformation transformation;
    private final Vector throwVector;
    private final long dismountAliveFor;

    private final Predicate<Entity> onHitEntity;
    private final Predicate<Vector> onHitBlock;

    public Throw(BlockData itemToThrow, Vector throwVector, long dismountAliveFor, Predicate<Entity> onHitEntity, Predicate<Vector> onHitBlock) {
        super("example lore", "example description");

        this.itemToThrow = itemToThrow;
        this.throwVector = throwVector;
        this.dismountAliveFor = dismountAliveFor;
        this.onHitEntity = onHitEntity;
        this.onHitBlock = onHitBlock;
    }

    public void setTransformation(Transformation transformation){
        this.transformation = transformation;
    }

    private boolean getColliding(World world, Location location, double maxDistance, int vehicleId, int selfId, int ballId){
        RayTraceResult rayTraceResult = world.rayTrace(
                location,
                location.getDirection(),
                maxDistance,
                FluidCollisionMode.NEVER,
                true,
                1,
                entity -> {
                    int id = entity.getEntityId();
                    return id != selfId && id != vehicleId && id != ballId;
                }
        );

        if (rayTraceResult == null){
            return false;
        }

        boolean killThrowable = false;
        Entity hitEntity = rayTraceResult.getHitEntity();
        if (hitEntity != null && onHitEntity != null){
            killThrowable = this.onHitEntity.test(hitEntity);
        }

        Block hitblock = rayTraceResult.getHitBlock();
        if (hitblock != null && onHitBlock != null){
            Vector hitPosition = rayTraceResult.getHitPosition();
            killThrowable = killThrowable || this.onHitBlock.test(hitPosition);
        }

        return killThrowable;
    }

    public void launch(Location spawnLocation, World world, float factor){
        BlockDisplay blockDisplay = (BlockDisplay) world.spawnEntity(spawnLocation, EntityType.BLOCK_DISPLAY);
        blockDisplay.setBlock(this.itemToThrow);
        blockDisplay.setRotation(spawnLocation.getYaw(), spawnLocation.getPitch());

        if (transformation != null)
            blockDisplay.setTransformation(this.transformation);

        Snowball snowball = (Snowball) world.spawnEntity(spawnLocation, EntityType.SNOWBALL);
        Helper.clientRemoveEntityPacket(snowball.getEntityId());
        snowball.setVelocity(this.throwVector.multiply(factor));

        ArmorStand armorStand = (ArmorStand) Helper.spawnInvisibleArmorStand(spawnLocation, true, true);
        armorStand.addPassenger(blockDisplay);
        armorStand.setVelocity(this.throwVector.multiply(factor));

        final int id1 = armorStand.getEntityId();
        final int id2 = blockDisplay.getEntityId();
        final int id3 = snowball.getEntityId();


        new BukkitRunnable(){
            long stayAliveForAfterDismount = dismountAliveFor;
            @Override
            public void run() {
                if(getColliding(world, armorStand.getLocation(), 1, id1, id2, id3)){

                    snowball.remove();
                    blockDisplay.remove();
                    armorStand.remove();
                    this.cancel();
                    return;
                }


                if (!blockDisplay.isInsideVehicle()){
                    if (stayAliveForAfterDismount-- <= 0)
                    {
                        blockDisplay.remove();
                        this.cancel();
                    }
                } else {
                    if (snowball.isDead()){
                        armorStand.remove();
                    } else {
                        armorStand.setVelocity(snowball.getVelocity());
                        Location location = snowball.getLocation();
                        blockDisplay.setRotation(location.getYaw(), location.getPitch());
                    }
                }
            }
        }.runTaskTimer(MinecraftSmashHeroes.PLUGIN, 0, 1);

    }
}
