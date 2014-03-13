package net.wtako.VoidSpawn.Commands;

import net.wtako.VoidSpawn.Commands.Voidspawn.ArgHelp;
import net.wtako.VoidSpawn.Commands.Voidspawn.ArgReload;
import net.wtako.VoidSpawn.Commands.Voidspawn.ArgRemove;
import net.wtako.VoidSpawn.Commands.Voidspawn.ArgSet;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandVoidlimit implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length >= 1) {
            if (args[0].equalsIgnoreCase("reload")) {
                new ArgReload(sender);
                return true;
            } else if (args[0].equalsIgnoreCase("set")) {
                new ArgSet(sender, args);
                return true;
            } else if (args[0].equalsIgnoreCase("remove")) {
                new ArgRemove(sender, args);
                return true;
            } else if (args[0].equalsIgnoreCase("help") || args[0].equalsIgnoreCase("?")) {
                new ArgHelp(sender);
                return true;
            }
        }
        return false;
    }
}
