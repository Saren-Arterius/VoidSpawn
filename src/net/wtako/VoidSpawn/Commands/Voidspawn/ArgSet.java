package net.wtako.VoidSpawn.Commands.Voidspawn;

import java.text.MessageFormat;
import java.util.List;

import net.wtako.VoidSpawn.Main;
import net.wtako.VoidSpawn.Utils.Lang;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ArgSet {

    public ArgSet(CommandSender sender, String[] args) {
        int yLimit;

        if (!sender.hasPermission("VoidSpawn.set")) {
            sender.sendMessage(Lang.NO_PERMISSION_COMMAND.toString());
            return;
        }

        final Location location = ((Player) sender).getLocation();

        try {
            yLimit = Integer.parseInt(args[1]);
        } catch (final ArrayIndexOutOfBoundsException e) {
            yLimit = location.getBlockY();
        } catch (final NumberFormatException e) {
            sender.sendMessage(Lang.SET_USAGE.toString());
            return;
        }

        if (location.getWorld().getSpawnLocation().getBlockY() <= yLimit) {
            sender.sendMessage(MessageFormat.format(Lang.MUST_BE_LOWER_THAN_SPAWN.toString(), yLimit, location
                    .getWorld().getSpawnLocation().getBlockY()));
            return;
        }

        final List<String> currentEnabledWorlds = Main.getInstance().getConfig().getStringList("worlds.EnabledWorlds");
        if (!currentEnabledWorlds.contains(location.getWorld().getName())) {
            currentEnabledWorlds.add(location.getWorld().getName());
        }
        Main.getInstance().getConfig().set("worlds.EnabledWorlds", currentEnabledWorlds);
        Main.getInstance().getConfig()
                .set(MessageFormat.format("worlds.WorldYLimits.{0}", location.getWorld().getName()), yLimit);
        Main.getInstance().saveConfig();
        sender.sendMessage(MessageFormat.format(Lang.VALUE_SET.toString(), location.getWorld().getName(), yLimit));
    }

}
