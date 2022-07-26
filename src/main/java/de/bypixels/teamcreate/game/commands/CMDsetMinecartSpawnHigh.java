package de.bypixels.teamcreate.game.commands;

import de.bypixels.teamcreate.game.main.MinecartRain;
import de.bypixels.teamcreate.game.util.DataAboutGame;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class CMDsetMinecartSpawnHigh implements CommandExecutor {

    //Command der die Höhe der Minecarts zum Spawnen setzt
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, Command command, @NotNull String s, String[] args) {
        if (command.getName().equalsIgnoreCase("highminecartspawn")) {
            if (commandSender instanceof Player player) {
                if (player.hasPermission("highminecartspawn")) {

                    DataAboutGame.setHighWhereMinecartsSpawn(player);
                    DataAboutGame.getCfg().set("highWhereMinecartsSpawn", (int) player.getLocation().getY());
                    try {
                        DataAboutGame.getCfg().save(DataAboutGame.getFile());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    player.sendMessage(MinecartRain.PREFIX + "§7Du hast die Höhe der Minecarts zum Spawnen gesetzt!");
                    }else{
                    player.sendMessage(MinecartRain.getPREFIX()+ "§cDu hast nicht die passenden Rechte um diesen Befehl benutzen!");
                }
                }
            }


        return false;
    }
}
