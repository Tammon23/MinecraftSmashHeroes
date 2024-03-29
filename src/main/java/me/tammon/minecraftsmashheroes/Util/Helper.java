package me.tammon.minecraftsmashheroes.Util;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.network.protocol.game.ClientboundRemoveEntitiesPacket;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_20_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_20_R3.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class Helper {
    public static ItemStack getDyedLeatherArmour(Material armourPiece, int red, int green, int blue){
        ItemStack item = new ItemStack(armourPiece);
        LeatherArmorMeta itemMeta = (LeatherArmorMeta)item.getItemMeta();
        itemMeta.setColor(Color.fromRGB(red, green, blue));
        item.setItemMeta(itemMeta);

        return item;
    }

    public static Entity spawnInvisibleArmorStand(Location location, boolean makeSmall, boolean noclip){
        ServerLevel w = ((CraftWorld)location.getWorld()).getHandle();
        ArmorStand armorStand = new ArmorStand(w, location.getX(), location.getY(), location.getZ());
        armorStand.setXRot(location.getYaw());
        armorStand.setYRot(location.getPitch());
        armorStand.setInvisible(true);

        if (makeSmall)
            armorStand.setSmall(true);

        if (noclip)
            armorStand.noPhysics = true;

        w.addFreshEntity(armorStand, CreatureSpawnEvent.SpawnReason.CUSTOM);

        return armorStand.getBukkitEntity();
    }
    public static void clientRemoveEntityPacket(int id){
        for (Player p: Bukkit.getServer().getOnlinePlayers()){
            ClientboundRemoveEntitiesPacket packet = new ClientboundRemoveEntitiesPacket(id);
            ((CraftPlayer) p).getHandle().connection.send(packet);
        }
    }
}
