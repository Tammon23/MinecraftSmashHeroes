package me.tammon.minecraftsmashheroes.Commands;

import me.tammon.minecraftsmashheroes.Heroes.CustomHeroes.Botmon.Botmon;
import me.tammon.minecraftsmashheroes.Heroes.CustomHeroes.Bulk.Bulk;
import me.tammon.minecraftsmashheroes.Heroes.CustomHeroes.CakeMonster.CakeMonster;
import me.tammon.minecraftsmashheroes.Heroes.CustomHeroes.GeneralCluck.GeneralCluck;
import me.tammon.minecraftsmashheroes.Heroes.Hero;
import me.tammon.minecraftsmashheroes.Heroes.HeroNameEnum;
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

            if (args.length > 0){
                boolean use_masterskin = args.length !=1 && args[1].toLowerCase().equals("true");
                Hero selected;
                HeroNameEnum selected_hero_enum;

                try {
                    selected_hero_enum = HeroNameEnum.valueOf(args[0].toUpperCase());
                }
                catch (IllegalArgumentException e) {
                    player.sendMessage(String.format("Unknown hero: %s", args[0].toLowerCase()));
                    return true;
                }

                switch (selected_hero_enum){
                    case BULK:
                        selected = new Bulk(player, Prestige.ZERO);
                        selected.ApplyOutfit(use_masterskin);
                        break;

                    case GENERAL_CLUCK:
                        selected = new GeneralCluck(player, Prestige.ZERO);
                        selected.ApplyOutfit(use_masterskin);
                        break;

                    case CAKE_MONSTER:
                        selected = new CakeMonster(player, Prestige.ZERO);
                        selected.ApplyOutfit(use_masterskin);
                        break;

                    case BOTMON:
                        selected = new Botmon(player, Prestige.ZERO);
                        selected.ApplyOutfit(use_masterskin);
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
