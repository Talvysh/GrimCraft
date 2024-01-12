package io.github.talvysh.region

import io.github.talvysh.GrimCraft
import org.bukkit.Chunk
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin
import java.util.logging.Level

open class Region {
    private val members = mutableSetOf<Player>()
    private val chunks = mutableSetOf<Chunk>()

    companion object {
        private val plugin : Plugin = GrimCraft.ref
        val instances = mutableSetOf<Region>()

        fun isChunkClaimed(chunk : Chunk) : Boolean {
            instances.forEach {
                if (it.containsChunk(chunk))
                    return true
            }
            return false
        }

        fun getRegionByChunk(chunk : Chunk) : Region? {
            // Should call isChunkClaimed() before calling this function
            instances.forEach {
                if (it.containsChunk(chunk)) {
                    return it
                }
            }
            return null
        }
    }

    constructor(){
        instances.add(this)
    }

    constructor(data : String){
        instances.add(this)
    }

    fun addChunk(chunk : Chunk) {
        instances.forEach {
            if (it.containsChunk(chunk))
                plugin.logger.log(Level.INFO, "This chunk is already claimed.")
                return
        }

        chunks.add(chunk)
        plugin.logger.log(Level.INFO, "Chunk claimed.")
    }

    fun removeChunk(chunk : Chunk) {
        if (!containsChunk(chunk)){
            plugin.logger.log(Level.INFO, "This region does not own that chunk.")
            return
        }

        chunks.remove(chunk)
    }

    fun containsChunk(chunk : Chunk) : Boolean {
        return chunks.contains(chunk)
    }

    fun isMember(player : Player) : Boolean {
        return members.contains(player)
    }
}
