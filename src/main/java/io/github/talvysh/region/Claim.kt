package io.github.talvysh.region

import org.bukkit.Chunk

// A data class to help keep track of Chunks.
// I believe Chunks are volatile, so you cannot do "chunk == chunk".
// They're always changing, so we have to make our own chunks
class Claim {
    var x: Int = 0
    var z: Int = 0
    var world: String = ""
    var region: Region? = null

    companion object {
        val instances = mutableSetOf<Claim>()

        fun hasChunky(x : Int, z : Int, world : String) : Boolean {
            instances.forEach {
                if (it.x == x && it.z == z && it.world == world)
                    return true
            }
            return false
        }

        fun getClaim(chunk: Chunk): Claim {
            instances.forEach {
                if (it.x == chunk.x && it.z == chunk.z && it.world == chunk.world.name)
                    return it
            }

            return Claim(chunk.x, chunk.z, chunk.world.name)
        }

        fun getClaim(x : Int, z : Int, world : String): Claim {
            instances.forEach {
                if (it.x == x && it.z == z && it.world == world)
                    return it
            }

            return Claim(x, z, world)
        }
    }

    constructor(x : Int, z : Int, world : String) {
        // Create Chunky data for the first time

        // Check that this unique location doesn't already exist
        if (hasChunky(x, z, world))
            return

        // Setup basic variables
        this.x = x
        this.z = z
        this.world = world

        // Add the instance to the collection
        instances.add(this)
    }

    constructor(data : String){
        // Load Chunky data from a database

        // Check that this unique location doesn't already exist
        if (hasChunky(x, z, world))
            return

        this.x = 0
        this.z = 0

        instances.add(this)
    }

    fun save(){}
}