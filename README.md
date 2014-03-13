VoidSpawn
========

This is a Craftbukkit plugin. When a player's Y position is too low in some worlds, teleport him to spawn.

Usage
========

* Prevents players from dying in the void.
* You have a beautiful spawn in empty world. You are too lazy to set WorldGuard protections everywhere and you don't want players to type commands to get back up.

Commands
========
* **/voidspawn reload** - Reloads the plugin.
* **/voidspawn set [Y limit]** - set this world's Y limit to your Y position [or specified value]
* **/voidspawn remove [world1] [world2] ...** - disables limit in this world [or all specified worlds]

Permissions
========

    VoidSpawn.*:
        description: Gives access to all VoidSpawn commands.
        default: op
    VoidSpawn.admin:
        description: Gives access to all VoidSpawn administrative commands.
        children:
            VoidSpawn.reload: true
            VoidSpawn.set: true
            VoidSpawn.bypass: true
        default: op
    VoidSpawn.set:
        description: Gives access to set Y position limit.
        default: op
    VoidSpawn.bypass:
        description: Gives access to bypass Y position limit.
        default: op
    VoidSpawn.reload:
        description: Reloads the plugin.
        default: op
