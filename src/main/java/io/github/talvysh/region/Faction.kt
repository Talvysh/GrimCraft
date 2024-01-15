package io.github.talvysh.region

import io.github.talvysh.Character
import io.github.talvysh.GrimCraft
import org.bukkit.block.Block
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import java.sql.ResultSet

/*
* The faction system is setup so that a solo player
* can take down zergs if they want to.  There's no "faction power",
* or over the type mechanics to grind out what you need to raid
* another faction.
* */
class Faction : Region {
    lateinit var leader: Character
    var members = mutableSetOf<Character>()
    var officers = mutableSetOf<Character>()
    lateinit var heart : Block

    companion object {
        private val plugin: JavaPlugin = GrimCraft.ref
        var instances: MutableList<Faction?> = ArrayList()
    }

    constructor(leader: Character, name: String) : super(name) {
        /* Creation of a new Faction */

        if (leader.faction != null) {
            leader.player.sendMessage("")
            return
        }

        this.name = name
        this.leader = leader

        instances.add(this)
    }

    constructor(result: ResultSet) : super(result.getString("name")) {

    }

    fun invite(player: Player?) {
    }

    fun kick(player: Player?) {
    }

    fun promote(player: Player?) {
    }

    fun demote(player: Player?) {
    }

    fun isMember(character: Character): Boolean {
        /* Checks if a player is part of this faction */

        return members.contains(character)
    }

    fun delete() {
        /* Completely removes the faction and deletes it from the DB */

        // TODO: Implement DB
    }

    fun highlight() {
        /* Causes the heart to glow, helping raiders find the heart when they are within range */
    }

    fun convert(){
        // Converts a faction to a server region.
        // The best use of this is when a faction region looks amazing
        // and the faction is no longer playing, but we want to save the build
    }
}
