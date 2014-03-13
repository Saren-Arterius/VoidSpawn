package net.wtako.VoidSpawn.Schedulers;

import net.wtako.VoidSpawn.Main;
import net.wtako.VoidSpawn.EventHandlers.PlayerMoveListener;
import net.wtako.VoidSpawn.Utils.Lang;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class TeleportationTask extends BukkitRunnable {

    private final Player   player;
    private final Location location;
    private Integer        cycleLeft;

    public TeleportationTask(Player player) {
        this.player = player;
        cycleLeft = Main.getInstance().getConfig().getInt("variable.Cycle");
        location = player.getWorld().getSpawnLocation();
        location.setPitch(player.getLocation().getPitch());
        location.setYaw(player.getLocation().getYaw());
        if (Main.getInstance().getConfig().getBoolean("variable.EnableConfusionEffect")) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 1000000, 1));
        }
        runTaskTimer(Main.getInstance(), 0, Main.getInstance().getConfig().getInt("variable.TicksPerCycle"));
    }

    @Override
    public void run() {
        if (cycleLeft > 0) {
            cycleLeft--;
            player.getWorld().playEffect(player.getLocation(), Effect.SMOKE, 31);
        } else {
            TeleportationTask.end(player);
            player.setVelocity(new Vector(0, 0, 0));
            player.teleport(location);
            if (Main.getInstance().getConfig().getBoolean("variable.EnableMessageOnSpawn")) {
                player.sendMessage(Lang.SPAWN_MESSAGE.toString());
            }
        }
    }

    public static void end(Player player) {
        if (Main.getInstance().getConfig().getBoolean("variable.EnableConfusionEffect")) {
            player.removePotionEffect(PotionEffectType.CONFUSION);
        }
        PlayerMoveListener.getInTPProcess().remove(player.getName()).cancel();
    }
}