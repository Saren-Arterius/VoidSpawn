package net.wtako.VoidSpawn.EventHandlers;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;

import net.wtako.VoidSpawn.Main;
import net.wtako.VoidSpawn.Schedulers.TeleportationTask;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener implements Listener {

    private static HashMap<String, TeleportationTask> inTPProcess = new HashMap<String, TeleportationTask>();

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        final Player player = event.getPlayer();

        if (player.hasPermission("VoidSpawn.bypass")) {
            return;
        }

        final List<String> enabledWorlds = Main.getInstance().getConfig().getStringList("worlds.EnabledWorlds");

        if (!enabledWorlds.contains(player.getLocation().getWorld().getName())) {
            return;
        }

        if (player.getLocation().getBlockY() <= Main.getInstance().getConfig()
                .getInt(MessageFormat.format("worlds.WorldYLimits.{0}", player.getLocation().getWorld().getName()))
                && !PlayerMoveListener.inTPProcess.containsKey(player.getName())) {
            PlayerMoveListener.inTPProcess.put(player.getName(), new TeleportationTask(player));
        } else if (player.getLocation().getBlockY() > Main.getInstance().getConfig()
                .getInt(MessageFormat.format("worlds.WorldYLimits.{0}", player.getLocation().getWorld().getName()))
                && PlayerMoveListener.inTPProcess.containsKey(player.getName())) {
            TeleportationTask.end(player);
        }
    }

    @EventHandler
    public void onFallDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            final Player victim = (Player) event.getEntity();
            if (TeleportationTask.getNoFallDamage().contains(victim.getName())) {
                if (event.getCause() == EntityDamageEvent.DamageCause.FALL) {
                    event.setCancelled(true);
                    TeleportationTask.getNoFallDamage().remove(victim.getName());
                }
            }
        }
    }

    public static HashMap<String, TeleportationTask> getInTPProcess() {
        return PlayerMoveListener.inTPProcess;
    }
}
