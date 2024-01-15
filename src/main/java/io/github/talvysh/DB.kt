package io.github.talvysh

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import java.sql.Connection
import java.sql.SQLException

public object DB {
    /* Make sure we're doing connection pooling for better performance */
    private var connectionString: String? = null
    private lateinit var ds : HikariDataSource
    private lateinit var hikari : HikariConfig

    fun init() {
        val config = GrimCraft.ref!!.config
        val host = config.getString("database.host")!!
        val port = config.getString("database.port")!!
        val name = config.getString("database.name")!!
        val username = config.getString("database.username")!!
        val password = config.getString("database.password")

        hikari = HikariConfig()

        hikari.jdbcUrl = "jdbc:mysql://"
        hikari.username = name
        hikari.password = password
        hikari.addDataSourceProperty("cachePrepStmts", "true")
        hikari.addDataSourceProperty("prepStmtCacheSize", "250")
        hikari.addDataSourceProperty("prepStmtCacheSqlLimit", "2048")

        ds = HikariDataSource(hikari)

        // Putting the URL together
        connectionString = "jdbc:mysql://"
        connectionString += "$host:$port/$name?"
        connectionString += "user=$username"

        if (password != null) {
            connectionString += "&password=$password"
        }
    }

    @Throws(SQLException::class)
    fun connect(): Connection? {
        return ds.connection
    }
}