VoidSpawn
========

This is a Craftbukkit plugin. A much simpler version of CreeperHeal.

Usage
========

* Create some explosions.
* ???
* PROFIT!!!

Commands
========
* **/seh reload** - Reloads the plugin.

Permissions
========

    VoidSpawn.*:
        description: Gives access to all VoidSpawn commands.
        default: op
    VoidSpawn.admin:
        description: Gives access to all VoidSpawn administrative commands.
        children:
            VoidSpawn.reload: true
        default: op
    VoidSpawn.reload:
        description: Reloads the plugin.
        default: op
