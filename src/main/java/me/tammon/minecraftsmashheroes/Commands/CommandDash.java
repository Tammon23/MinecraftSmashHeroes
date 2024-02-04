package me.tammon.minecraftsmashheroes.Commands;

import me.tammon.minecraftsmashheroes.Features.Dash;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandDash implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
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
            case "quickdash":
                if (args.length == 1)
                    Dash.quickDash(player);
                else
                    Dash.quickDash(player, Float.parseFloat(args[1]));
                break;

            default:
                player.sendMessage("Improper usage, unhandled option: " + args[0]);

        }
        return true;
    }
}
