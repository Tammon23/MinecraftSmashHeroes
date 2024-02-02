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

    private final int segment_spacing;
    private final int segment_length;
    private final BlockData segment_material;

    private int line_length;
    private final long max_life;
    private final Collection<Entity> segments = new ArrayList<>();
    private Transformation segment_transformation = new Transformation(
            new Vector3f(-1, -1, 0),
            new Quaternionf(0, 1, 0, 1),
            new Vector3f(1, 1, 1),
            new Quaternionf()
    );

    public DrawLine(World world, Location start, Location end, int segment_spacing, int segment_length, BlockData segment_material, long max_life){
        this.world = world;
        this.start = start;
        this.end = end;
        this.segment_spacing = segment_spacing;
        this.segment_length = segment_length;
        this.segment_material = segment_material;
        this.max_life = max_life;
    }

    public DrawLine(World world, Location start, int line_length, int segment_spacing, int segment_length, BlockData segment_material, long max_life){
        this.world = world;
        this.start = start;
        this.line_length = line_length;
        this.segment_spacing = segment_spacing;
        this.segment_length = segment_length;
        this.segment_material = segment_material;
        this.max_life = max_life;
    }

    public void setSegment_transformation(Transformation transformation){
        this.segment_transformation = transformation;
    }

    public void draw(){
        final int number_of_segments;
        if (this.end != null){
            final double distance = this.start.distance(end);
            number_of_segments = (int) ((distance + this.segment_spacing) / (this.segment_spacing + this.segment_length));
        }
        else {
            number_of_segments = (this.line_length + this.segment_spacing) / (this.segment_spacing + this.segment_length);
        }
        drawHelper(number_of_segments);
    }

    private void drawHelper(int number_of_segments){
        final Vector jump_value = this.start.getDirection().normalize().multiply(this.segment_spacing + this.segment_length);
        final float yaw = this.start.getYaw();
        final float pitch = this.start.getPitch();

        for (int i = 0; i < number_of_segments; i++){
            BlockDisplay blockDisplay = (BlockDisplay) this.world.spawnEntity(
                    this.start,
                    EntityType.BLOCK_DISPLAY
            );
            blockDisplay.setBlock(this.segment_material);
            blockDisplay.setRotation(yaw, pitch);
            blockDisplay.setTransformation(this.segment_transformation);
            this.segments.add(blockDisplay);

            this.start.add(jump_value);
        }

        // kill each line segment after max_life ticks
        Bukkit.getScheduler().runTaskLater(MinecraftSmashHeroes.PLUGIN, () -> {
            for (Entity entity : this.segments){
                entity.remove();
            }
        }, this.max_life);
    }
}
