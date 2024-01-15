package io.github.talvysh

import io.github.talvysh.region.Region
import org.bukkit.plugin.java.JavaPlugin
import java.util.logging.Level

class GrimCraft : JavaPlugin() {

    companion object {
        lateinit var ref: GrimCraft
    }

    override fun onEnable() {
        ref = this

        // Plugin startup logic
        logger.log(Level.INFO, "GrimCraft enabled!")

        connectListeners()
        connectCommands()

        Region.initialize()

        val connection = DB.connect()
        val prep = connection!!.prepareStatement("")
        prep.executeQuery()
    }

    override fun onDisable() {
        // Plugin shutdown logic
        // TODO: Make necessary DB calls.
    }

    fun connectListeners(){}

    fun connectCommands(){}
}
