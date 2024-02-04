package me.tammon.minecraftsmashheroes.Features;

import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

public class Dash {

    public static void quickDash(Entity entity){
        quickDash(entity, 1);
    }
    public static void quickDash(Entity entity, float dashFactor){
        Vector unitDirection = entity.getLocation().getDirection().normalize();
        entity.setVelocity(unitDirection.multiply(dashFactor));
    }
}
