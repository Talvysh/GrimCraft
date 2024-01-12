package io.github.talvysh

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.plugin.Plugin


// Would be smart to cache what chunk the player is in
// this way we don't have to keep making dozens of pointless
// container searches

class GuardianEvents : Listener {
    var plugin : Plugin = GrimCraft.ref

    /* Provides various protection to the server. */
    @EventHandler
    fun onBlockBreak(e : BlockBreakEvent) {

    }

    @EventHandler
    fun onBlockPlace() {
        /**/

        // Players can place ladders, but they will be removed after 1 hour.
        // Keep track of placed ladders.  All ladders will be removed no
        // matter how long if server shuts down.
    }

    @EventHandler
    fun onLiquidPlaced() {
        // Lava and water source blocks can be placed and are
        // removed after 10 minutes.
    }

    @EventHandler
    fun onLiquidInteract() {
    }
}
