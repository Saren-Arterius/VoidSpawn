package net.wtako.VoidSpawn.Utils;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 * An enum for requesting strings from the language file.
 * 
 * @author gomeow
 */
public enum Lang {

    TITLE("title", "[VoidSpawn]"),
    PLUGIN_RELOADED("plugin-reloaded", "&aPlugin reloaded."),
    SPAWN_MESSAGE("spawn-message", "&eYou are teleported to spawn."),
    SET_USAGE("set-usage", "Usage: &a/voidspawn set &f[&cY limit&f]"),
    MUST_BE_LOWER_THAN_SPAWN(
            "must-be-lower-than-spawn",
            "&cThe Y limit value ({0}) must be smaller than spawns Y position ({1})."),
    HELP_TEXT1(
            "help-text1",
            "Type &a/voidspawn set &f[&cY limit&f] to set this world's Y limit to your Y position [or specified value]"),
    HELP_TEXT2(
            "help-text2",
            "Type &a/voidspawn remove &f[&cworld1&f] &f[&cworld2&f] ... to disable limit in this world [or all specified worlds]"),
    HELP_TEXT3("help-text3", "Type &a/voidspawn reload &fto reload this plugin."),
    VALUE_SET("value-set", "&aValue set. {0}: {1}"),
    NO_PERMISSION_COMMAND("no-permission-command", "&cYou are not allowed to use this command.");

    private String                   path;
    private String                   def;
    private static YamlConfiguration LANG;

    /**
     * Lang enum constructor.
     * 
     * @param path
     *            The string path.
     * @param start
     *            The default string.
     */
    Lang(String path, String start) {
        this.path = path;
        def = start;
    }

    /**
     * Set the {@code YamlConfiguration} to use.
     * 
     * @param config
     *            The config to set.
     */
    public static void setFile(YamlConfiguration config) {
        Lang.LANG = config;
    }

    @Override
    public String toString() {
        if (this == TITLE) {
            return ChatColor.translateAlternateColorCodes('&', Lang.LANG.getString(path, def)) + " ";
        }
        return ChatColor.translateAlternateColorCodes('&', Lang.LANG.getString(path, def));
    }

    /**
     * Get the default value of the path.
     * 
     * @return The default value of the path.
     */
    public String getDefault() {
        return def;
    }

    /**
     * Get the path to the string.
     * 
     * @return The path to the string.
     */
    public String getPath() {
        return path;
    }
}