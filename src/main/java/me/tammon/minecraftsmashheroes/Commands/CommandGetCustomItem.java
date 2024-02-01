package me.tammon.minecraftsmashheroes.Commands;

import me.tammon.minecraftsmashheroes.Features.CustomAbilities.SkullfireGun;
import me.tammon.minecraftsmashheroes.Features.CustomAbilities.TinmanShooter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
        switch (args[0].toLowerCase()){
            case "skullfire_gun":
                player.getInventory().addItem(SkullfireGun.getSkullfire_gun());
                break;

            case "tinman_shooter":
                player.getInventory().addItem(TinmanShooter.getTinman_shooter());
                break;

            default:
                player.sendMessage(String.format("unknown item name %s", args[0].toLowerCase()));
                break;
        }

        return true;
    }
}
