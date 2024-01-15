package io.github.talvysh

import io.github.talvysh.region.Faction
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import java.sql.ResultSet
import java.sql.SQLException
import java.util.logging.Level

class Character {
    /* Character Class
     *  */
    var faction: Faction? = null  // Faction reference the player is part of.
    lateinit var player: Player  // Bukkit Player reference.
    val editing: Boolean = false  // Toggle whether the player can interact within claimed/wild chunks

    companion object {
        val plugin: JavaPlugin = GrimCraft.ref
        val instances = mutableSetOf<Character>()

        enum class Rank {
            PLAYER,
            SUPPORTER,
            ADMIN,
            OWNER
        }

        fun init() {
            /* Called in onEnable() of the plugin
            *  Loads and creates characters from the database */

            val connection = DB.connect()
            val statement = connection!!.prepareStatement("")

            try {
                val con = DB.connect()!!
                val s = con.prepareStatement("SELECT * FROM members")
                val results = s.executeQuery()

                while (results.next()) {
                    Character(results)
                }
            } catch (e: SQLException) {
                plugin.logger.log(Level.SEVERE, "Failed to connect to the server in Member.init()!")
            }
        }

        fun getCharacter(player: Player): Character {
            /* Can be used to check if a member exists or not.  Returns
        * a member object if successful. */

            for (m in instances) {
                if (m.player == player) return m
            }

            return Character(player)
        }
    }

    constructor(player: Player) {
        /* New members created on first-time login */
        instances.add(this)
        this.player = player
    }

    constructor(res: ResultSet) {
        /* Members created from the DB */
        val uuid = res.getString("uuid")
        player = plugin.server.getPlayer(uuid)!!  // If the member has already connected, this should not return null.
    }

    fun delete() {
        /* Delete the player's Member data
        *  This can be useful when permanently banning a player */

        // TODO: Remove from DB
    }
}
