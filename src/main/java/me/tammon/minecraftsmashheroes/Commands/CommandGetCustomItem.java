package me.tammon.minecraftsmashheroes.Commands;

import me.tammon.minecraftsmashheroes.Features.CustomAbilities.Gun;
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
                player.getInventory().addItem(Gun.getSkullfire_gun());
                break;

            default:
                player.sendMessage("unknown item name %s".formatted(args[0].toLowerCase()));
                break;
        }

        return true;
    }
}
