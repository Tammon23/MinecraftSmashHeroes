package me.tammon.minecraftsmashheroes.Events.Tinman;

import me.tammon.minecraftsmashheroes.Features.Bullet;
import me.tammon.minecraftsmashheroes.Heroes.CustomHeroes.Tinman.TinmanShooter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Transformation;

public class ShooterUseEvent implements Listener {
    @EventHandler
    public static void onRightClick(PlayerInteractEvent event){
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK ||
                event.getAction() == Action.RIGHT_CLICK_AIR){
            if (event.getItem() != null){
                if (event.getItem().getItemMeta().equals(TinmanShooter.getTinmanShooter().getItemMeta())) {
                    Player player = event.getPlayer();
                    player.playSound(player, "tinman.blastershot", 1f, 1f);


                    Bullet bullet = new Bullet(player,
                            Material.BLUE_STAINED_GLASS.createBlockData(),
                            TinmanShooter.getMaxBulletLife(),
                            1,
                            (entity, vector) -> {player.sendMessage("hit entity " + entity.getName() + " at: " + vector); return true;},
                            (block, vector) -> {player.sendMessage("hit block " + block + " at: " + vector); return true;});


                    Transformation transformation = bullet.getTransformation();
                    transformation.getTranslation().x = 1f;
                    transformation.getTranslation().y = -1f;
                    transformation.getLeftRotation().y = -1f;

                    bullet.setTransformation(transformation);

                    bullet.animate();
                }
            }
        }
    }
}
