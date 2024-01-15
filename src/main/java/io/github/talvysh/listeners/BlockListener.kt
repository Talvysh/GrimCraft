package io.github.talvysh.listeners

import io.github.talvysh.Character
import io.github.talvysh.region.Claim
import org.bukkit.block.Block
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockPlaceEvent

class BlockListener : Listener {
    fun canBuild(player: Player, block: Block): Boolean {
        if (block.world.name != "")
            return true

        val claim = Claim.getClaim(block.x, block.z, block.world.name)
        val character = Character.getMember(player)

        if (claim.region == null){
            if (player.hasPermission("grimcraft.builder")){
                if (!character.editing) return false
            }
            else return false
        }

        if (claim.region != character.faction)
            return false

        return true
    }

    @EventHandler
    fun onBlockBreak(event: BlockBreakEvent){
        event.isCancelled = !canBuild(event.player, event.block)
    }

    @EventHandler
    fun onBlockPlace(event: BlockPlaceEvent){
        event.isCancelled = !canBuild(event.player, event.block)
    }
}