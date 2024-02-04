package me.tammon.minecraftsmashheroes.Features;

import me.tammon.minecraftsmashheroes.MinecraftSmashHeroes;
import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class Dash {

    public static void smooth(Entity entity){
        smooth(entity, 1);
    }
    public static void smooth(Entity entity, float dashFactor){
        Vector unitDirection = entity.getLocation().getDirection().normalize();
        entity.setVelocity(unitDirection.multiply(dashFactor));
    }

    public static void quick(Entity entity, long maxTicks){
        quick(entity, 1, maxTicks);
    }

    public static void quick(Entity entity, float dashFactor, long maxTicks){
        Vector unitDirection = entity.getLocation().getDirection().normalize().multiply(dashFactor);
        new BukkitRunnable() {
            long ticksLeft = maxTicks;
            @Override
            public void run() {
                if (ticksLeft <= 0){
                    entity.setVelocity(new Vector());
                    this.cancel();
                }

                entity.setVelocity(unitDirection);
                ticksLeft--;
            }
        }.runTaskTimer(MinecraftSmashHeroes.PLUGIN,0L, 1L);
    }

    public static void forward(Entity entity, long maxTicks){
        forward(entity, 1, maxTicks);
    }
    public static void forward(Entity entity, float dashFactor, long maxTicks){
        new BukkitRunnable() {
            long ticksLeft = maxTicks;
            @Override
            public void run() {
                Vector unitDirection = entity.getLocation().getDirection().normalize().multiply(dashFactor);

                if (ticksLeft <= 0){
                    entity.setVelocity(new Vector());
                    this.cancel();
                }

                entity.setVelocity(unitDirection);
                ticksLeft--;
            }
        }.runTaskTimer(MinecraftSmashHeroes.PLUGIN,0L, 1L);
    }
}
