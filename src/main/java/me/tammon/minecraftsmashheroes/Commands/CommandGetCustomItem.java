package me.tammon.minecraftsmashheroes.Commands;

import me.tammon.minecraftsmashheroes.Features.ItemLoadingBar;
import me.tammon.minecraftsmashheroes.Features.Powerups.SmashCrystal;
import me.tammon.minecraftsmashheroes.Features.Throw;
import me.tammon.minecraftsmashheroes.Heroes.CustomHeroes.GeneralCluck.Blaster;
import me.tammon.minecraftsmashheroes.Heroes.CustomHeroes.Karakot.Kamehameha;
import me.tammon.minecraftsmashheroes.Heroes.CustomHeroes.Shoop.ShoopShooter;
import me.tammon.minecraftsmashheroes.Heroes.CustomHeroes.Skullfire.SkullfireGun;
import me.tammon.minecraftsmashheroes.Heroes.CustomHeroes.Tinman.TinmanShooter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.util.Transformation;
import org.joml.AxisAngle4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class CommandGetCustomItem implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)){
            sender.sendMessage("Only players can use this command");
            return true;
        }

        if (args.length == 0){
            sender.sendMessage("Improper usage, expected argument");
            return true;
        }

        Player player = (Player) sender;
        Inventory playerInventory = player.getInventory();
        switch (args[0].toLowerCase()){
            case "skullfire_gun":
                playerInventory.addItem(SkullfireGun.getSkullfireGun());
                break;

            case "tinman_shooter":
                playerInventory.addItem(TinmanShooter.getTinmanShooter());
                break;

            case "shoop_shooter":
                playerInventory.addItem(ShoopShooter.getShoopShooter());
                break;

            case "karakot_kamehamega":
                playerInventory.addItem(Kamehameha.getKamehamehaHand());
                break;

            case "general_cluck_blaster":
                playerInventory.addItem(Blaster.get_general_cluck_blaster());
                break;

            case "smash_crystal":
                float x = Float.parseFloat(args[1]);
                float y = Float.parseFloat(args[2]);
                float z = Float.parseFloat(args[3]);
                long countdownTimer = Long.parseLong(args[4]);

                SmashCrystal smashCrystal = new SmashCrystal(
                        player.getWorld(),
                        new Location(player.getWorld(), x, y, z),
                        Material.PINK_STAINED_GLASS_PANE.createBlockData(),
                        Material.RED_CARPET.createBlockData(),
                        countdownTimer
                );

                smashCrystal.start();
                break;

            case "item_loading_bar":
                long duration  = Long.parseLong(args[1]);
                int slot = Integer.parseInt(args[2]); // 0-8 hot bar, 9-17 top row
                ItemLoadingBar itemLoadingBar = new ItemLoadingBar(Material.IRON_SHOVEL, Material.ORANGE_DYE, duration, slot);
                itemLoadingBar.start(player);
                playerInventory.setItem(slot, itemLoadingBar.getItemTimer());
                break;

            case "throw":
                Location location = player.getLocation();
                location.setY(location.getY() + 10);
                Throw throwme = new Throw(Material.DARK_PRISMARINE.createBlockData(), location.getDirection().normalize(), 60,
                    entity -> {
                        Bukkit.broadcastMessage("Hit entity: " + entity);
                        return true;
                    },
                    block -> {
                        Bukkit.broadcastMessage("Hit block: " + block);
                        return false;
                    }
                );
                throwme.setTransformation( new Transformation(
                        new Vector3f(1, -1, 1),
                        new Quaternionf(0, -1, 0, 1),
                        new Vector3f(1, 1, 1),
                        new Quaternionf(0, 0, 0, 1)
                ));
                throwme.launch(location, player.getWorld(), Float.parseFloat(args[1]));
                break;

            default:
                player.sendMessage(String.format("unknown item name %s", args[0].toLowerCase()));
                break;
        }

        return true;
    }
}
