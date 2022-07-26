package de.bypixels.teamcreate.game.events;

import de.bypixels.teamcreate.game.main.MinecartRain;
import de.bypixels.teamcreate.game.util.DataAboutArena;
import de.bypixels.teamcreate.game.util.DataAboutGame;
import de.bypixels.teamcreate.game.util.api.specialEvents.PlayerWinEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class WinPlayerMove implements Listener {


    //
    @EventHandler
    public void onPlayerMoveToWin(PlayerWinEvent event) {
        Player player = event.getPlayer();
        for (Player all : Bukkit.getOnlinePlayers()) {
            all.sendMessage(MinecartRain.PREFIX + "§7Der Spieler: §6" + player.getName() + " §7hat das Ziel erreicht!");
        }

        //Fügt den Spieler in die Winnerliste ein!
        MinecartRain.getWinner().add(event.getPlayer().getName());
        if (!MinecartRain.getWinner().contains(event.getPlayer()))
            MinecartRain.getWinner().add(event.getPlayer().getName());


        World world = Bukkit.getWorld(DataAboutArena.getBackInArenaWorldName());
        Location backInGameLoc = new Location(world, DataAboutArena.getBackInArenaX(), DataAboutArena.getBackInArenaY(), DataAboutArena.getBackInArenaZ());

        //Teleports the Player back into the old Arena.
        player.teleport(backInGameLoc);

        player.sendMessage(MinecartRain.PREFIX + "§7Du hast gewonnen und bist zurück im Spiel!");

        MinecartRain.getPlayingPlayers().remove(player);

        if (MinecartRain.getWinner().size() == DataAboutGame.getAmountOfPlayerToStop() || MinecartRain.getPlayingPlayers().isEmpty()) {

            Bukkit.getScheduler().scheduleSyncDelayedTask(MinecartRain.getPlugin(), () -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "stoprain"), 40);

        }

        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 2, 2);

    }






}
