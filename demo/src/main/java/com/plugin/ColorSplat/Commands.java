package com.plugin.ColorSplat;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {

    public static int game = 0;
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("startsplat")){
            if (sender instanceof Player) {
                game = 1;
                sender.sendMessage("ColorSplat: Game Started");
            }
            return true;
        }
        if(cmd.getName().equalsIgnoreCase("endSplat")){
             if (sender instanceof Player) {
                game = 0;
                if (game == 0){
                    sender.sendMessage("ColorSplat: Game Ended");
                }
            }
            return true;
        }
        return false;
    }
}
