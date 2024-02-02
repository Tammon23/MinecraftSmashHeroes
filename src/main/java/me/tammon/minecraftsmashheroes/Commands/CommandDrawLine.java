package me.tammon.minecraftsmashheroes.Commands;

import me.tammon.minecraftsmashheroes.Features.DrawLine;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandDrawLine implements CommandExecutor {
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

        DrawLine drawLine = new DrawLine(
                player.getWorld(),
                player.getLocation(),
                Integer.parseInt(args[0]), // end
                0,
                1,
                Material.DARK_OAK_SAPLING.createBlockData(),
                Long.parseLong(args[1])
        );

        drawLine.draw();

        return true;
    }
}
