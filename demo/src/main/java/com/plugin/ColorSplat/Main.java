package com.plugin.ColorSplat;


import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class Main extends JavaPlugin implements Listener{

    FileConfiguration config = this.getConfig();


    @Override
    public void onEnable() {
        //Incase it does not exsit
        saveDefaultConfig();
        reloadConfig();

        getServer().getPluginManager().registerEvents(this, this);
        getCommand("endSplat").setExecutor(new Commands());
        getCommand("startSplat").setExecutor(new Commands());
        getLogger().info("ColorSplat: Enabled Plugin");

    }
    @Override
    public void onDisable() {
        saveConfig();
        getLogger().info("ColorSplat: Disabled Plugin");
    }
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        // Get the player's location
        if (Commands.game == 1){
            int playerX = event.getTo().getBlockX();
            int playerY = event.getTo().getBlockY();
            int playerZ = event.getTo().getBlockZ();

            // Check if the player's location has a water block below it
            if (event.getPlayer().getWorld().getBlockAt(playerX, playerY -1 , playerZ).getType() == Material.WATER) {
                // Set the block below the player to wool
                event.getPlayer().getWorld().getBlockAt(playerX, playerY -1, playerZ).setType(Material.WHITE_WOOL);
            }
        }
    }
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        // Loop through all blocks in config radius
        if (Commands.game == 1){
           Player player = event.getEntity();
            int deathX = player.getLocation().getBlockX();
            int deathY = player.getLocation().getBlockY();
            int deathZ = player.getLocation().getBlockZ();
            //Gets the resetradius or uses 25
            int radius = config.getInt("resetradius", 25);

            for (int x = deathX - radius; x <= deathX + radius; x++) {
                for (int y = deathY - radius; y <= deathY + radius; y++) {
                    for (int z = deathZ - radius; z <= deathZ + radius; z++) {
                        if (player.getWorld().getBlockAt(x, y, z).getType() == Material.WHITE_WOOL) {
                            player.getWorld().getBlockAt(x, y, z).setType(Material.WATER);
                        }
                    }
                }
            }
        }
    }
}