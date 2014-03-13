package net.wtako.VoidSpawn.Commands.Voidspawn;

import net.wtako.VoidSpawn.Utils.Lang;

import org.bukkit.command.CommandSender;

public class ArgHelp {

    public ArgHelp(CommandSender sender) {
        sender.sendMessage("Void Spawn v0.0.1");
        sender.sendMessage("Author: Saren");
        sender.sendMessage("Aliases: vs");
        sender.sendMessage(Lang.HELP_TEXT1.toString());
        sender.sendMessage(Lang.HELP_TEXT2.toString());
        sender.sendMessage(Lang.HELP_TEXT3.toString());
    }

}
