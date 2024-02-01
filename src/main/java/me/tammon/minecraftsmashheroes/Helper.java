package me.tammon.minecraftsmashheroes;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.decoration.ArmorStand;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_20_R3.CraftWorld;
import org.bukkit.entity.Entity;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class Helper {
    public static ItemStack GetDyedLeatherArmour(Material armour_piece, int red, int green, int blue){
        ItemStack item = new ItemStack(armour_piece);
        LeatherArmorMeta item_meta = (LeatherArmorMeta)item.getItemMeta();
        item_meta.setColor(Color.fromRGB(red, green, blue));
        item.setItemMeta(item_meta);

        return item;
    }

    public static Entity SpawnInvisibleArmorStand(Location location, boolean make_small, boolean noclip){
        ServerLevel w = ((CraftWorld)location.getWorld()).getHandle();
        ArmorStand armorStand = new ArmorStand(w, location.getX(), location.getY(), location.getZ());
        armorStand.setXRot(location.getYaw());
        armorStand.setYRot(location.getPitch());
        armorStand.setInvisible(true);

        if (make_small)
            armorStand.setSmall(true);

        if (noclip)
            armorStand.noPhysics = true;

        w.addFreshEntity(armorStand, CreatureSpawnEvent.SpawnReason.CUSTOM);

        return armorStand.getBukkitEntity();
    }
}
