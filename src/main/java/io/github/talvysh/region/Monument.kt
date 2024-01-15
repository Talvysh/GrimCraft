package io.github.talvysh.region

class Monument : Region {
    private val flags = mutableMapOf("pvp" to false, "hostile-mobs" to false)

    constructor(name: String) : super(name) {

    }

    fun setFlag(flag: String, value: Boolean) {
        flags[flag] = value
    }

    fun getFlag(flag: String): Boolean {
        return flags[flag]!!
    }
}