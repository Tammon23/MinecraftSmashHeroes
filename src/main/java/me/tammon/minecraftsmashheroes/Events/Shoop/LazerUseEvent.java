package me.tammon.minecraftsmashheroes.Events.Shoop;

import me.tammon.minecraftsmashheroes.Features.Bullet;
import me.tammon.minecraftsmashheroes.Heroes.CustomHeroes.Shoop.ShoopShooter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Transformation;

public class LazerUseEvent implements Listener {
    @EventHandler
    public static void onRightClick(PlayerInteractEvent event){
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK ||
                event.getAction() == Action.RIGHT_CLICK_AIR){
            if (event.getItem() != null){
                if (event.getItem().getItemMeta().equals(ShoopShooter.getShoopShooter().getItemMeta())) {
                    Player player = event.getPlayer();
                    player.playSound(player, "shoopdawhoop.active", 1f, 1f);

                    Bullet bullet = new Bullet(player,
                            Material.PURPLE_WOOL.createBlockData(),
                            ShoopShooter.getMaxBulletLife(),
                            ShoopShooter.getSpeed(),
                            (entity, vector) -> {player.sendMessage("hit entity " + entity.getName() + " at: " + vector); return true;},
                            (block, vector) -> {player.sendMessage("hit block " + block + " at: " + vector); return true;});

                    Transformation transformation = bullet.getTransformation();
                    transformation.getTranslation().x = -0.5f;
                    transformation.getTranslation().y = -0.5f;

                    bullet.setTransformation(transformation);

                    bullet.animate();
                }
            }
        }
    }
}

