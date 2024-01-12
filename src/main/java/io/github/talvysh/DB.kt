package io.github.talvysh

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import java.util.logging.Level

object DB {
    /* Make sure we're doing connection pooling for better performance */
    private var connectionString: String? = null

    fun init() {
        val config = GrimCraft.ref.config
        val host = config.getString("database.host")!!
        val port = config.getString("database.port")!!
        val name = config.getString("database.name")!!
        val username = config.getString("database.username")!!
        val password = config.getString("database.password")

        connectionString = "jdbc:mysql://"
        connectionString += "$host:$port/$name?"
        connectionString += "user=$username"

        if (password != null) {
            connectionString += "&password=$password"
        }
    }

    fun connect(): Connection? {
        // TODO: Implement pooling
        try {
            val connection = DriverManager.getConnection(connectionString)

            if (!connection.isValid(1)) {
                GrimCraft.ref.logger.log(Level.SEVERE, "Connection to DB is invalid!")
                return null
            }

            return connection
        } catch (exception: SQLException) {
            GrimCraft.ref.logger.log(Level.SEVERE, "No connection could be made to the server!")
            GrimCraft.ref.logger.log(Level.SEVERE, "Connection string: $connectionString")
            return null
        }
    }
}