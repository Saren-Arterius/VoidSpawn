package net.wtako.VoidSpawn.Schedulers;

import java.util.ArrayList;

import net.wtako.HoloPopup.Utils.Lang;
import net.wtako.VoidSpawn.Main;
import net.wtako.VoidSpawn.EventHandlers.PlayerMoveListener;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class TeleportationTask extends BukkitRunnable {

    private static ArrayList<String> noFallDamage = new ArrayList<String>();
    private final Player             player;
    private final Location           location;
    private Integer                  cycleLeft;

    public TeleportationTask(Player player) {
        this.player = player;
        cycleLeft = Main.getInstance().getConfig().getInt("variable.Cycle");
        location = player.getWorld().getSpawnLocation();
        location.setPitch(player.getLocation().getPitch());
        location.setYaw(player.getLocation().getYaw());
        if (Main.getInstance().getConfig().getBoolean("variable.EnableConfusionEffect")) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 1000000, 1));
        }
        TeleportationTask.noFallDamage.add(player.getName());
        runTaskTimer(Main.getInstance(), 0, Main.getInstance().getConfig().getInt("variable.TicksPerCycle"));
    }

    @Override
    public void run() {
        if (cycleLeft > 0) {
            cycleLeft--;
            player.getWorld().playEffect(player.getLocation(), Effect.SMOKE, 31);
        } else {
            player.teleport(location);
            TeleportationTask.end(player);
            if (Main.getInstance().getConfig().getBoolean("variable.EnableMessageOnSpawn")) {
                player.sendMessage(Lang.SPAWN_MESSAGE.toString());
            }
        }
    }

    public static void end(final Player player) {
        if (Main.getInstance().getConfig().getBoolean("variable.EnableConfusionEffect")) {
            player.removePotionEffect(PotionEffectType.CONFUSION);
        }
        PlayerMoveListener.getInTPProcess().remove(player.getName()).cancel();
        Main.getInstance().getServer().getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
            @Override
            public void run() {
                if (!PlayerMoveListener.getInTPProcess().containsKey(player.getName())) {
                    TeleportationTask.noFallDamage.remove(player.getName());
                }
            }
        }, 20);
    }

    public static ArrayList<String> getNoFallDamage() {
        return TeleportationTask.noFallDamage;
    }

}