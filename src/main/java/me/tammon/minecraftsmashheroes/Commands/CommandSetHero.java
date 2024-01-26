package me.tammon.minecraftsmashheroes.Commands;

import me.tammon.minecraftsmashheroes.Heroes.CustomHeroes.Bulk;
import me.tammon.minecraftsmashheroes.Heroes.Hero;
import me.tammon.minecraftsmashheroes.Heroes.Prestige;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSetHero implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){
            Player player = (Player) sender;

            if (args.length > 1){
                Hero selected;
                switch (args[0].toLowerCase()){
                    case "bulk":
                        selected = new Bulk(player, Prestige.ZERO);
                        selected.ApplyOutfit(false);
                        break;
                    default:
                        player.sendMessage(String.format("Unknown hero: %s", args[0].toLowerCase()));
                        break;
                }
            }
        }

        return true;
    }
}
