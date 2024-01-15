package io.github.talvysh.region

import io.github.talvysh.GrimCraft
import org.bukkit.plugin.Plugin
import java.sql.ResultSet

open class Region {
    private val claims = mutableSetOf<Claim>()
    var name: String = ""  // Used in code and in commands
    private var displayName: String = ""  // What players see

    companion object {
        private val plugin: Plugin = GrimCraft.ref
        private val instances = mutableSetOf<Region>()

        fun initialize(){
            val wilderness = Region("wilderness")
        }

        fun getRegion(claim: Claim): Region? {
            instances.forEach {
                if (it.claims.contains(claim)) {
                    return it
                }
            }
            return null
        }

        fun getRegion(name: String): Region? {
            instances.forEach {
                if (it.name == name)
                    return it
            }
            return null
        }
    }

    constructor(name: String){
        instances.add(this)
    }

    constructor(data: ResultSet){}

    fun addClaim(claim: Claim) {
        if(getRegion(claim) != null)
            return

        claims.add(claim)
    }

    fun removeClaim(claim : Claim) {
        if(claims.contains(claim))
            claims.remove(claim)
    }
}
