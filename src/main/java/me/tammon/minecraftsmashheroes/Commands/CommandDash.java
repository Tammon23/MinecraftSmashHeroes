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
            case "smooth":
                if (args.length == 1)
                    Dash.smooth(player);
                else
                    Dash.smooth(player, Float.parseFloat(args[1]));
                break;

            case "quick":
                if (args.length == 2)
                    Dash.quick(player, Long.parseLong(args[1]));

                else if (args.length > 2)
                    Dash.quick(player, Float.parseFloat(args[1]), Long.parseLong(args[2]));

                else
                    player.sendMessage("Improper usage not enough arguments");

                break;

            case "forward":
                if (args.length == 2)
                    Dash.forward(player, Long.parseLong(args[1]));

                else if (args.length > 2)
                    Dash.forward(player, Float.parseFloat(args[1]), Long.parseLong(args[2]));

                else
                    player.sendMessage("Improper usage not enough arguments");

            default:
                player.sendMessage("Improper usage, unhandled option: " + args[0]);

        }
        return true;
    }
}
