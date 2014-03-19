package net.wtako.VoidSpawn.Commands.Voidspawn;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.wtako.HoloPopup.Utils.Lang;
import net.wtako.VoidSpawn.Main;

import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ArgRemove {

    public ArgRemove(CommandSender sender, String[] args) {
        if (!sender.hasPermission("VoidSpawn.set")) {
            sender.sendMessage(Lang.NO_PERMISSION_COMMAND.toString());
            return;
        }

        final List<String> worldsToRemove = new ArrayList<String>(Arrays.asList(args));
        worldsToRemove.remove(0);

        final List<String> currentEnabledWorlds = Main.getInstance().getConfig().getStringList("worlds.EnabledWorlds");

        if (worldsToRemove.size() != 0) {
            for (final String worldToRemove: worldsToRemove) {
                currentEnabledWorlds.remove(worldToRemove);
            }
        } else {
            final World world = ((Player) sender).getLocation().getWorld();
            currentEnabledWorlds.remove(world.getName());
        }

        Main.getInstance().getConfig().set("worlds.EnabledWorlds", currentEnabledWorlds);
        Main.getInstance().saveConfig();
        sender.sendMessage(MessageFormat.format(Lang.VALUE_SET.toString(), "EnabledWorlds",
                currentEnabledWorlds.toString()));
    }

}
