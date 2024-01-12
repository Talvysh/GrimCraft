package io.github.talvysh.region.dungeon

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockPlaceEvent

class DungeonEvents : Listener {
    @EventHandler
    fun onBlockBreak(e: BlockBreakEvent) {
        val player : Player = e.player
        player.hasPermission("grimcraft.builder")

        if (Dungeon.getDungeon(e.block.location) != null) {
            e.isCancelled = true
        }
    }

    @EventHandler
    fun onBlockPlace(e: BlockPlaceEvent) {
        if (Dungeon.getDungeon(e.block.location) != null) {
            e.isCancelled = true
        }
    }
}
