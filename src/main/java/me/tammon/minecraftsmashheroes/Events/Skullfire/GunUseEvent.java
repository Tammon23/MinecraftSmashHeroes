package me.tammon.minecraftsmashheroes.Events.Skullfire;

import me.tammon.minecraftsmashheroes.Heroes.CustomHeroes.Skullfire.SkullfireGun;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.RayTraceResult;

public class GunUseEvent implements Listener {

    @EventHandler
    public static void onRightClick(PlayerInteractEvent event){
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR){
            if (event.getItem() != null){
                if (event.getItem().getItemMeta().equals(SkullfireGun.getSkullfireGun().getItemMeta())) {
                    Player player = event.getPlayer();
                    player.playSound(player.getLocation(), "skullfire.magnumshot", 1.0f, 1.0f);

                    RayTraceResult rayTraceResult = player.getWorld().rayTraceEntities(
                            player.getEyeLocation(),
                            player.getEyeLocation().getDirection(),
                            SkullfireGun.DAMAGE_DISTANCE,
                            entity -> entity.getEntityId() != player.getEntityId()
                    );

                    if (rayTraceResult != null){
                        player.sendMessage("Hit: " + rayTraceResult.getHitEntity());
                    }
                     else {
                         player.sendMessage("Miss");
                    }
                }
            }
        }
    }
}
