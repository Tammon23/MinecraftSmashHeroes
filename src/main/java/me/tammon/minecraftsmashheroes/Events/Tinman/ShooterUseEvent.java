package me.tammon.minecraftsmashheroes.Events.Tinman;

import me.tammon.minecraftsmashheroes.Features.Bullet;
import me.tammon.minecraftsmashheroes.Heroes.CustomHeroes.Tinman.TinmanShooter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class ShooterUseEvent implements Listener {
    @EventHandler
    public static void onRightClick(PlayerInteractEvent event){
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK ||
                event.getAction() == Action.RIGHT_CLICK_AIR){
            if (event.getItem() != null){
                if (event.getItem().getItemMeta().equals(TinmanShooter.getTinman_shooter().getItemMeta())) {
                    Player player = event.getPlayer();
                    player.playSound(player, "tinman.blastershot", 1f, 1f);


                    Bullet bullet = new Bullet(player,
                            Material.BLUE_STAINED_GLASS.createBlockData(),
                            TinmanShooter.getMax_bullet_life(),
                            1,
                            (entity, vector) -> {player.sendMessage("hit entity " + entity.getName() + " at: " + vector); return true;},
                            (block, vector) -> {player.sendMessage("hit block " + block + " at: " + vector); return true;});


                    bullet.animate();
                }
            }
        }
    }
}
