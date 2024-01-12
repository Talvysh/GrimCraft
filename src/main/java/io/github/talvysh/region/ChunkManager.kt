package io.github.talvysh.region

import org.bukkit.Chunk

object ChunkManager {
    // Tracks all instances of Chunky
    private val chunkies = mutableSetOf<Chunky>()

    fun getChunky(x : Int, z : Int, world : String) : Chunky {
        // Searches for a Chunky already being tracked
        // If it doesn't find one, it creates a new one and returns that
        chunkies.forEach {
            if (it.x == x && it.z == z && it.world == world)
                return it
        }
        val c = Chunky(x, z, world)
        return c
    }

    fun getRegion(chunk : Chunk) : Region? {
        // Gets the region of the Chunky, can be null
        // Can be used to check if a chunk is available or not
        return getChunky(chunk.x, chunk.z, chunk.world.name).region
    }

    fun setRegion(chunk : Chunk, region : Region) {
        // Sets the region of the Chunky to the passed parameter
        getChunky(chunk.x, chunk.z, chunk.world.name).region = region
    }
}