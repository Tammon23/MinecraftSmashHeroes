package me.tammon.minecraftsmashheroes.Commands;

import me.tammon.minecraftsmashheroes.Features.Rideable;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSummonVehicle implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)){
            sender.sendMessage("Only players can use this command");
            return true;
        }

        if (args.length < 2){
            sender.sendMessage("Improper usage, expected argument");
            return true;
        }

        Player player = (Player) sender;
        switch (args[0].toLowerCase()){
            case "botmobile_car":
                player.sendMessage("starting ride for " + Integer.parseInt(args[1]) / 20 + "s");
                Rideable ride = new Rideable(player, Material.MYCELIUM.createBlockData(), Integer.parseInt(args[1]));
                ride.mount(player);
                ride.animate();
                break;

            default:
                player.sendMessage("unknown item name %s".formatted(args[0].toLowerCase()));
                break;
        }

        return true;
    }
}
