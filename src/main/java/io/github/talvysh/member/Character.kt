package io.github.talvysh.member

import io.github.talvysh.GrimCraft
import io.github.talvysh.region.faction.Faction
import org.bukkit.Location
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import java.sql.ResultSet
import java.sql.SQLException
import java.util.logging.Level

class Character {
    var faction : Faction? = null
    lateinit var player : Player

    enum class Rank {
        PLAYER,
        SUPPORTER,
        ADMIN,
        OWNER
    }

    constructor(player: Player) {
        /* New members created on first-time login */

        if (getMember(player) == null) return
        instances!!.add(this)
        this.player = player
    }

    constructor(res: ResultSet) {
        /* Members created from the DB */
        val uuid = res.getString("uuid")
        player = plugin.server.getPlayer(uuid)!!  // If the member has already connected, this should not return null.
    }

    fun promote(admin: Player, other: Player) {
        // If not an Admin
        if (rank!! < Rank.ADMIN) {
            // Increase the rank by 1
            rank = Rank.entries[rank!!.ordinal + 1]
            plugin.logger.log(Level.INFO, admin.name + " promoted " + other.name + "'s server rank to " + rank.toString())
        }
    }

    fun demote(admin: Player, other: Player) {
        // If not a Player
        if (rank!! > Rank.PLAYER) {
            // Decrease the rank by 1
            rank = Rank.entries[rank!!.ordinal - 1]
            plugin.logger.log(Level.INFO, admin.name + " demoted " + other.name + "'s server rank to " + rank.toString())
        }
    }

    fun delete() {
        /* Delete the player's Member data */

        // TODO: Remove from DB
    }

    companion object {
        val plugin: JavaPlugin = GrimCraft.ref
        var instances: MutableList<Character>? = null

        fun init() {
            /* Called in onEnable() of the plugin */

            try {
                val con = SaveData.connect()!!
                val s = con.prepareStatement("SELECT * FROM members")
                val results = s.executeQuery()

                while (results.next()) {
                    Character(results)
                }
            } catch (e: SQLException) {
                plugin.logger.log(Level.SEVERE, "Failed to connect to the server in Member.init()!")
            }
        }

        fun getMember(player: Player): Character? {
            /* Can be used to check if a member exists or not.  Returns
        * a member object if successful. */

            for (m in instances!!) {
                if (m.player === player) return m
            }

            return null
        }
    }
}
