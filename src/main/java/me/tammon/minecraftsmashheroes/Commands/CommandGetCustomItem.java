package me.tammon.minecraftsmashheroes.Commands;

import me.tammon.minecraftsmashheroes.Events.Karakot.KarakotHandUse;
import me.tammon.minecraftsmashheroes.Heroes.CustomHeroes.GeneralCluck.Blaster;
import me.tammon.minecraftsmashheroes.Heroes.CustomHeroes.Karakot.Kamehameha;
import me.tammon.minecraftsmashheroes.Heroes.CustomHeroes.Shoop.ShoopShooter;
import me.tammon.minecraftsmashheroes.Heroes.CustomHeroes.Skullfire.SkullfireGun;
import me.tammon.minecraftsmashheroes.Heroes.CustomHeroes.Tinman.TinmanShooter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

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
        Inventory player_inventory = player.getInventory();
        switch (args[0].toLowerCase()){
            case "skullfire_gun":
                player_inventory.addItem(SkullfireGun.getSkullfire_gun());
                break;

            case "tinman_shooter":
                player_inventory.addItem(TinmanShooter.getTinman_shooter());
                break;

            case "shoop_shooter":
                player_inventory.addItem(ShoopShooter.getShoop_shooter());
                break;

            case "karakot_kamehamega":
                player_inventory.addItem(Kamehameha.get_KamehamehaHand());
                break;

            case "general_cluck_blaster":
                player_inventory.addItem(Blaster.get_general_cluck_blaster());
                break;

            default:
                player.sendMessage(String.format("unknown item name %s", args[0].toLowerCase()));
                break;
        }

        return true;
    }
}
