package io.github.talvysh.listeners

import io.github.talvysh.region.Claim
import io.github.talvysh.region.Monument
import io.github.talvysh.region.Region
import org.bukkit.entity.EntityType
import org.bukkit.entity.Monster
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityEnterBlockEvent
import org.bukkit.event.entity.EntitySpawnEvent

class EntityListener : Listener {
    companion object {
        val hostileTypes = setOf<EntityType>(
                EntityType.BLAZE, EntityType.CAVE_SPIDER,
                EntityType.ZOMBIE, EntityType.CREEPER
        )
    }

    @EventHandler
    fun onEntitySpawn(event: EntitySpawnEvent) {
        val claim = Claim.getClaim(event.location.chunk)
        if (claim.region == null)
            return

        // Check if the region is a monument
        val region = Region.getRegion(claim)
        if (region is Monument){

            // Check if the region doesn't allow hostile mob spawning
            if (!region.getFlag("hostile-mobs") && event.entity is Monster){
                event.isCancelled = true
            }
        }
    }

    @EventHandler
    fun onEntityEnter(event: EntityEnterBlockEvent){

    }
}