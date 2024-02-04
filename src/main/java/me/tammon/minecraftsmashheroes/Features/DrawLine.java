package me.tammon.minecraftsmashheroes.Features;

import me.tammon.minecraftsmashheroes.MinecraftSmashHeroes;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.*;
import org.bukkit.util.Transformation;
import org.bukkit.util.Vector;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.Collection;

public class DrawLine {
    private final World world;
    private final Location start;
    private Location end;

    private final int segmentSpacing;
    private final int segmentLength;
    private final BlockData segmentMaterial;

    private int lineLength;
    private final long maxLife;
    private final Collection<Entity> segments = new ArrayList<>();
    private Transformation segmentTransformation = new Transformation(
            new Vector3f(-1, -1, 0),
            new Quaternionf(0, 1, 0, 1),
            new Vector3f(1, 1, 1),
            new Quaternionf()
    );

    public DrawLine(World world, Location start, Location end, int segmentSpacing, int segmentLength, BlockData segmentMaterial, long maxLife){
        this.world = world;
        this.start = start;
        this.end = end;
        this.segmentSpacing = segmentSpacing;
        this.segmentLength = segmentLength;
        this.segmentMaterial = segmentMaterial;
        this.maxLife = maxLife;
    }

    public DrawLine(World world, Location start, int lineLength, int segmentSpacing, int segmentLength, BlockData segmentMaterial, long maxLife){
        this.world = world;
        this.start = start;
        this.lineLength = lineLength;
        this.segmentSpacing = segmentSpacing;
        this.segmentLength = segmentLength;
        this.segmentMaterial = segmentMaterial;
        this.maxLife = maxLife;
    }

    public void setSegmentTransformation(Transformation transformation){
        this.segmentTransformation = transformation;
    }

    public void draw(){
        final int numberOfSegments;
        if (this.end != null){
            final double distance = this.start.distance(end);
            numberOfSegments = (int) ((distance + this.segmentSpacing) / (this.segmentSpacing + this.segmentLength));
        }
        else {
            numberOfSegments = (this.lineLength + this.segmentSpacing) / (this.segmentSpacing + this.segmentLength);
        }
        drawHelper(numberOfSegments);
    }

    private void drawHelper(int numberOfSegments){
        final Vector jumpValue = this.start.getDirection().normalize().multiply(this.segmentSpacing + this.segmentLength);
        final float yaw = this.start.getYaw();
        final float pitch = this.start.getPitch();

        for (int i = 0; i < numberOfSegments; i++){
            BlockDisplay blockDisplay = (BlockDisplay) this.world.spawnEntity(
                    this.start,
                    EntityType.BLOCK_DISPLAY
            );
            blockDisplay.setBlock(this.segmentMaterial);
            blockDisplay.setRotation(yaw, pitch);
            blockDisplay.setTransformation(this.segmentTransformation);
            this.segments.add(blockDisplay);

            this.start.add(jumpValue);
        }

        // kill each line segment after max_life ticks
        Bukkit.getScheduler().runTaskLater(MinecraftSmashHeroes.PLUGIN, () -> {
            for (Entity entity : this.segments){
                entity.remove();
            }
        }, this.maxLife);
    }
}
